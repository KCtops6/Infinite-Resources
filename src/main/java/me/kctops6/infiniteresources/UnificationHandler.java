package me.kctops6.infiniteresources;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;

@EventBusSubscriber(modid = InfiniteResources.MOD_ID)
public class UnificationHandler {

    @SubscribeEvent
    public static void onItemPickup(ItemEntityPickupEvent.Pre event) {
        ItemEntity itemEntity = event.getItemEntity();
        ItemStack originalStack = itemEntity.getItem();
        ItemStack unifiedStack = getUnifiedStack(originalStack);

        if (!unifiedStack.isEmpty()) {
            itemEntity.setItem(unifiedStack);
        }
    }

    private static ItemStack getUnifiedStack(ItemStack originalStack) {
        if (originalStack.isEmpty()) return ItemStack.EMPTY;
        Item originalItem = originalStack.getItem();
        ResourceLocation itemKey = BuiltInRegistries.ITEM.getKey(originalItem);

        if (itemKey != null && itemKey.getNamespace().equals(InfiniteResources.MOD_ID)) return ItemStack.EMPTY;

        String[] forms = {"gem", "ingot", "nugget", "dust", "plate", "raw"};

        for (String material : ModItems.MATERIALS) {
            for (String form : forms) {
                if (!ModConfig.isItemEnabled(material, form)) continue;

                String tagLocation;
                if (form.equals("raw")) {
                    tagLocation = "c:raw_materials/" + material;
                } else if (form.equals("gem")) {
                    tagLocation = "c:gems/" + material;
                } else {
                    tagLocation = "c:" + form + "s/" + material;
                }

                if (hasTag(originalItem, tagLocation)) {
                    Item targetItem = getRegistryItem(material, form);
                    if (targetItem != null) {
                        return new ItemStack(targetItem, originalStack.getCount());
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private static boolean hasTag(Item item, String tagLocation) {
        TagKey<Item> tagKey = ItemTags.create(ResourceLocation.parse(tagLocation));
        Holder<Item> holder = BuiltInRegistries.ITEM.wrapAsHolder(item);
        return holder.is(tagKey);
    }

    private static Item getRegistryItem(String material, String form) {
        switch (form) {
            case "gem": return ModItems.GEMS.containsKey(material) ? ModItems.GEMS.get(material).get() : null;
            case "ingot": return ModItems.INGOTS.containsKey(material) ? ModItems.INGOTS.get(material).get() : null;
            case "nugget": return ModItems.NUGGETS.containsKey(material) ? ModItems.NUGGETS.get(material).get() : null;
            case "dust": return ModItems.DUSTS.containsKey(material) ? ModItems.DUSTS.get(material).get() : null;
            case "plate": return ModItems.PLATES.containsKey(material) ? ModItems.PLATES.get(material).get() : null;
            case "raw": return ModItems.RAW_ORES.containsKey(material) ? ModItems.RAW_ORES.get(material).get() : null;
            default: return null;
        }
    }
}