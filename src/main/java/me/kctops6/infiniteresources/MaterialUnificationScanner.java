package me.kctops6.infiniteresources;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

@Mod.EventBusSubscriber(modid = InfiniteResources.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MaterialUnificationScanner {
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            System.out.println("[" + InfiniteResources.MOD_ID + "] Scanning for external duplicate metals...");
            for (String material : ModItems.MATERIALS) {
                TagKey<Item> ingotTagKey = ItemTags.create(new ResourceLocation("forge", "ingots/" + material));
                ITag<Item> tag = ForgeRegistries.ITEMS.tags().getTag(ingotTagKey);
                boolean foundExternalMod = false;
                if (!tag.isEmpty()) {
                    for (Item item : tag) {
                        ResourceLocation itemKey = ForgeRegistries.ITEMS.getKey(item);
                        if (itemKey != null && !itemKey.getNamespace().equals(InfiniteResources.MOD_ID)) {
                            foundExternalMod = true;
                            break;
                        }
                    }
                }
                if (!foundExternalMod) {
                    ModConfig.setRuntimeOverride(material, false);
                    System.out.println("[" + InfiniteResources.MOD_ID + "] Auto-Disabled orphaned material: " + material);
                }
            }
        });
    }
}