package me.kctops6.infiniteresources;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

@Mod.EventBusSubscriber(modid = InfiniteResources.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class UnificationHandler {

    @SubscribeEvent
    public static void onItemPickup(EntityItemPickupEvent event) {
        ItemEntity itemEntity = event.getItem();
        ItemStack originalStack = itemEntity.getItem();
        ItemStack unifiedStack = getUnifiedStack(originalStack);

        if (!unifiedStack.isEmpty()) {
            itemEntity.setItem(unifiedStack);
        }
    }

    private static ItemStack getUnifiedStack(ItemStack originalStack) {
        if (originalStack.isEmpty()) return ItemStack.EMPTY;
        Item originalItem = originalStack.getItem();
        ResourceLocation itemKey = ForgeRegistries.ITEMS.getKey(originalItem);

        if (itemKey != null && itemKey.getNamespace().equals(InfiniteResources.MOD_ID)) return ItemStack.EMPTY;

        String[] forms = {"gem", "ingot", "nugget", "dust", "plate", "raw"};

        for (String material : ModItems.MATERIALS) {
            for (String form : forms) {
                if (!ModConfig.isItemEnabled(material, form)) continue;

                String tagLocation;
                if (form.equals("raw")) {
                    tagLocation = "forge:raw_materials/" + material;
                } else if (form.equals("gem")) {
                    tagLocation = "forge:gems/" + material;
                } else {
                    tagLocation = "forge:" + form + "s/" + material;
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
        TagKey<Item> tagKey = ItemTags.create(new ResourceLocation(tagLocation));
        ITag<Item> tag = ForgeRegistries.ITEMS.tags().getTag(tagKey);
        return !tag.isEmpty() && tag.contains(item);
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