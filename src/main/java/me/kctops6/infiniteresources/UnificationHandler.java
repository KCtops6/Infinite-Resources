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
        if (itemKey != null && itemKey.getNamespace().equals(InfiniteResources.MOD_ID)) return ItemStack.EMPTY;
        for (String material : ModItems.MATERIALS) {
            if (!ModConfig.isMaterialEnabled(material)) continue;
            if (hasTag(originalItem, "forge:ingots/" + material)) {
                return new ItemStack(ModItems.INGOTS.get(material).get(), originalStack.getCount());
            }
            if (hasTag(originalItem, "forge:nuggets/" + material)) {
                return new ItemStack(ModItems.NUGGETS.get(material).get(), originalStack.getCount());
            }
            if (hasTag(originalItem, "forge:dusts/" + material)) {
                return new ItemStack(ModItems.DUSTS.get(material).get(), originalStack.getCount());
            }
            if (hasTag(originalItem, "forge:plates/" + material)) {
                return new ItemStack(ModItems.PLATES.get(material).get(), originalStack.getCount());
            }
        }
        return ItemStack.EMPTY;
    }
    private static boolean hasTag(Item item, String tagLocation) {
        TagKey<Item> tagKey = ItemTags.create(new ResourceLocation(tagLocation));
        return ForgeRegistries.ITEMS.tags().getTag(tagKey).contains(item);
    }
}