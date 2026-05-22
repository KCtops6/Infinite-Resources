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
            if (ModItems.NUGGETS.containsKey(material)) { //
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_nugget_gold")); //
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_nugget_copper")); //
            }
            // Raw Ores
            if (ModItems.RAW_ORES.containsKey(material)) { //
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/raw_" + material + "_gold")); //
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/raw_" + material + "_copper")); //
            }
            // Gems
            if (ModItems.GEMS.containsKey(material)) {
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_gem_emerald"));
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_gem_quartz"));
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_gem_amethyst"));
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_gem_lapis"));
            }
        }
    }

    @SubscribeEvent
    public static void onModifyBakingResult(ModelEvent.ModifyBakingResult event) {
        for (String material : ModItems.MATERIALS) {

            // 1. Nugget Model Overrides with Unique Default Base Shapes
            if (ModItems.NUGGETS.containsKey(material)) { //
                NuggetStyle style = ModConfig.getNuggetStyle(material); //
                ModelResourceLocation itemLoc = new ModelResourceLocation(InfiniteResources.MOD_ID, material + "_nugget", "inventory"); //

                String styleName;
                if (style != NuggetStyle.IRON) { //
                    styleName = style.name().toLowerCase();
                } else {
                    // Hand-pick a baseline layout style for the default config state
                    styleName = getDefaultShapeForNugget(material);
                }

                // If it resolves to "iron", do not override (let it load the baseline template_nugget.json)
                if (!styleName.equals("iron")) {
                    ResourceLocation targetPath = new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_nugget_" + styleName);
                    var baked = event.getModels().get(targetPath);
                    if (baked != null) event.getModels().put(itemLoc, baked);
                }
            }

            // 2. Raw Ore Model Overrides with Unique Default Base Shapes
            if (ModItems.RAW_ORES.containsKey(material)) { //
                RawOreStyle rawStyle = ModConfig.getRawOreStyle(material); //
                ModelResourceLocation itemLoc = new ModelResourceLocation(InfiniteResources.MOD_ID, "raw_" + material, "inventory"); //

                String styleName;
                if (rawStyle != RawOreStyle.IRON) { //
                    styleName = rawStyle.name().toLowerCase();
                } else {
                    // Hand-pick a baseline layout style for the default config state
                    styleName = getDefaultShapeForRawOre(material);
                }

                // If it resolves to "iron", do not override (let it load the baseline raw_template.json)
                if (!styleName.equals("iron")) {
                    ResourceLocation targetPath = new ResourceLocation(InfiniteResources.MOD_ID, "item/raw_" + material + "_" + styleName);
                    var baked = event.getModels().get(targetPath);
                    if (baked != null) event.getModels().put(itemLoc, baked);
                }
            }

            // 3. Dynamic Gem Overrides with Unique Default Base Shapes
            if (ModItems.GEMS.containsKey(material)) {
                GemStyle gemStyle = ModConfig.getGemStyle(material);
                ModelResourceLocation itemLoc = new ModelResourceLocation(InfiniteResources.MOD_ID, material + "_gem", "inventory");

                String styleName;
                if (gemStyle != GemStyle.DIAMOND) {
                    styleName = gemStyle.name().toLowerCase();
                } else {
                    styleName = getDefaultShapeForGem(material);
                }

                if (!styleName.equals("diamond")) {
                    ResourceLocation targetPath = new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_gem_" + styleName);
                    var baked = event.getModels().get(targetPath);
                    if (baked != null) event.getModels().put(itemLoc, baked);
                }
            }
        }
    }

    /**
     * Helper method determining the baseline layout shape for nuggets
     * when the configuration file remains set to IRON.
     */
    /**
     * Determines the baseline layout shape for nuggets when the configuration
     * file remains set to IRON. Materials are evenly divided (10 each) across shapes.
     */
    private static String getDefaultShapeForNugget(String material) {
        switch (material) {
            // Group 1: Gold Nugget Shape (10 materials)
            case "gold":
            case "brass":
            case "bronze":
            case "electrum":
            case "ruby":
            case "topaz":
            case "citrine":
            case "amber":
            case "tigerseye":
            case "sunstone":
                return "gold";

            // Group 2: Copper Nugget Shape (10 materials)
            case "copper":
            case "lead":
            case "constantan":
            case "garnet":
            case "jasper":
            case "carnelian":
            case "amethyst":
            case "tanzanite":
            case "tourmaline":
            case "morganite":
                return "copper";

            // Group 3: Iron Nugget Shape (10 materials)
            // iron, diamond, emerald, lapis, steel, tin, silver, nickel, aluminum, zinc, invar, opal, aquamarine, peridot, jade, malachite, onyx, agate, turquoise, moonstone, iolite, alexandrite
            default:
                return "iron";
        }
    }

    /**
     * Helper method determining the baseline layout shape for raw ores
     * when the configuration file remains set to IRON.
     */
    private static String getDefaultShapeForRawOre(String material) {
        switch (material) {
            case "silver":
            case "zinc":
                return "gold";  // zinc, silver

            case "aluminum":
            case "tin":
                return "copper";    // tin, aluminum

            default:
                return "iron";  // nickel, lead
        }
    }

    /**
     * Helper method determining the baseline layout shape for every gem
     * when the configuration file remains set to DIAMOND.
     */
    private static String getDefaultShapeForGem(String material) {
        switch (material) {
            case "ruby":
            case "garnet":
            case "jasper":
            case "carnelian":
                return "emerald"; // Red gems utilize the pointier emerald layout

            case "sapphire":
            case "aquamarine":
            case "iolite":
                return "lapis"; // Blue gems fallback to the round lapis texture

            case "topaz":
            case "citrine":
            case "amber":
            case "tigerseye":
                return "quartz"; // Yellow/Orange gems use the crystalline quartz form

            case "amethyst":
            case "tanzanite":
                return "amethyst"; // Purple gems default to the cluster shard template

            default:
                return "diamond"; // Default baseline shape
        }
    }
}