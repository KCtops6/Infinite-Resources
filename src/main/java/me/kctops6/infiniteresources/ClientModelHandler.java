package me.kctops6.infiniteresources;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InfiniteResources.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModelHandler {

    // 1. Tell Minecraft to pre-load your custom style models into memory cache so they don't load as missing checkers
    @SubscribeEvent
    public static void onRegisterAdditionalModels(ModelEvent.RegisterAdditional event) {
        for (String material : ModItems.MATERIALS) {
            if (!ModItems.NUGGETS.containsKey(material)) continue;

            event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_nugget_gold"));
            event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_nugget_copper"));
        }
    }

    // 2. FIX: Intercept the final item map block using the correct 1.20.1 Forge event name
    @SubscribeEvent
    public static void onModifyBakingResult(ModelEvent.ModifyBakingResult event) {
        for (String material : ModItems.MATERIALS) {
            if (!ModItems.NUGGETS.containsKey(material)) continue;

            NuggetStyle style = ModConfig.getNuggetStyle(material);
            if (style == NuggetStyle.IRON) continue; // Default iron uses standard file path automatically

            // Master asset pointer identity location
            ModelResourceLocation inventoryItemLocation =
                    new ModelResourceLocation(InfiniteResources.MOD_ID, material + "_nugget", "inventory");

            // Variant redirect locations
            ResourceLocation targetVariantPath = style == NuggetStyle.GOLD
                    ? new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_nugget_gold")
                    : new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_nugget_copper");

            // Inject and overwrite the model reference mapping safely
            var customBakedModel = event.getModels().get(targetVariantPath);
            if (customBakedModel != null) {
                event.getModels().put(inventoryItemLocation, customBakedModel);
            }
        }
    }
}