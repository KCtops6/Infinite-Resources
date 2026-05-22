package me.kctops6.infiniteresources;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InfiniteResources.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModelHandler {

    @SubscribeEvent
    public static void onRegisterAdditionalModels(ModelEvent.RegisterAdditional event) {
        for (String material : ModItems.MATERIALS) {
            // Nuggets
            if (ModItems.NUGGETS.containsKey(material)) {
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_nugget_gold"));
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_nugget_copper"));
            }
            // Raw Ores
            if (ModItems.RAW_ORES.containsKey(material)) {
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/raw_" + material + "_gold"));
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/raw_" + material + "_copper"));
            }
        }
    }

    @SubscribeEvent
    public static void onModifyBakingResult(ModelEvent.ModifyBakingResult event) {
        for (String material : ModItems.MATERIALS) {

            // 1. Nugget Model Overrides
            if (ModItems.NUGGETS.containsKey(material)) {
                NuggetStyle style = ModConfig.getNuggetStyle(material);
                if (style != NuggetStyle.IRON) {
                    ModelResourceLocation itemLoc = new ModelResourceLocation(InfiniteResources.MOD_ID, material + "_nugget", "inventory");
                    ResourceLocation targetPath = style == NuggetStyle.GOLD
                            ? new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_nugget_gold")
                            : new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_nugget_copper");
                    var baked = event.getModels().get(targetPath);
                    if (baked != null) event.getModels().put(itemLoc, baked);
                }
            }

            // 2. Raw Ore Model Overrides
            if (ModItems.RAW_ORES.containsKey(material)) {
                RawOreStyle rawStyle = ModConfig.getRawOreStyle(material);
                if (rawStyle != RawOreStyle.IRON) {
                    // Note: Registry key matches "raw_steel", not "steel_raw"
                    ModelResourceLocation itemLoc = new ModelResourceLocation(InfiniteResources.MOD_ID, "raw_" + material, "inventory");
                    ResourceLocation targetPath = rawStyle == RawOreStyle.GOLD
                            ? new ResourceLocation(InfiniteResources.MOD_ID, "item/raw_" + material + "_gold")
                            : new ResourceLocation(InfiniteResources.MOD_ID, "item/raw_" + material + "_copper");
                    var baked = event.getModels().get(targetPath);
                    if (baked != null) event.getModels().put(itemLoc, baked);
                }
            }
        }
    }
}