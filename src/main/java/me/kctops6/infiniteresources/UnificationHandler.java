package me.kctops6.infiniteresources;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = InfiniteResources.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class UnificationHandler {

    @SubscribeEvent
    public static void onItemPickup(PlayerEvent.ItemPickupEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) return;

        ItemStack pickedUpStack = event.getStack();
        ItemStack unifiedStack = getUnifiedStack(pickedUpStack);

        if (!unifiedStack.isEmpty() && unifiedStack.getItem() != pickedUpStack.getItem()) {
            event.getOriginalEntity().setItem(unifiedStack);
        }
    }

    private static ItemStack getUnifiedStack(ItemStack originalStack) {
        if (originalStack.isEmpty()) return ItemStack.EMPTY;

        Item originalItem = originalStack.getItem();
        ResourceLocation itemKey = ForgeRegistries.ITEMS.getKey(originalItem);

        // Skip tracking completely if the object is native to this mod workspace
        if (itemKey != null && itemKey.getNamespace().equals(InfiniteResources.MOD_ID)) return ItemStack.EMPTY;

        String[] forms = {"ingot", "nugget", "dust", "plate"};

        for (String material : ModItems.MATERIALS) {
            if (!ModConfig.isMaterialEnabled(material)) continue;

            for (String form : forms) {
                // Pluralizes the directory naming path for proper Forge tag matching (e.g., "ingot" -> "ingots")
                String tagPath = form + "s/" + material;

                if (hasTag(originalItem, "forge:" + tagPath)) {
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
        return Objects.requireNonNull(ForgeRegistries.ITEMS.tags()).getTag(tagKey).contains(item);
    }

    private static Item getRegistryItem(String material, String form) {
        return switch (form) {
            case "ingot" -> ModItems.INGOTS.containsKey(material) ? ModItems.INGOTS.get(material).get() : null;
            case "nugget" -> ModItems.NUGGETS.containsKey(material) ? ModItems.NUGGETS.get(material).get() : null;
            case "dust" -> ModItems.DUSTS.get(material).get();
            case "plate" -> ModItems.PLATES.get(material).get();
            default -> null;
        };
    }
}