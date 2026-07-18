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
            // Gems
            if (ModItems.GEMS.containsKey(material)) {
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_gem_emerald"));
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_gem_quartz"));
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_gem_amethyst"));
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_gem_lapis"));
            }
        }

        ModBlocks.STORAGE_BLOCKS.keySet().forEach(material -> {
            boolean isGem = isGemstone(material);
            if (!isGem) {
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "block/" + material + "_block_iron"));
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "block/" + material + "_block_gold"));
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "block/" + material + "_block_copper"));
            } else {
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "block/" + material + "_block_diamond"));
                event.register(new ResourceLocation(InfiniteResources.MOD_ID, "block/" + material + "_block_emerald"));
            }
        });
    }

    @SubscribeEvent
    public static void onModifyBakingResult(ModelEvent.ModifyBakingResult event) {
        for (String material : ModItems.MATERIALS) {

            // 1. Nugget Model Overrides
            if (ModItems.NUGGETS.containsKey(material)) {
                NuggetStyle style = ModConfig.getNuggetStyle(material);
                ModelResourceLocation itemLoc = new ModelResourceLocation(InfiniteResources.MOD_ID, material + "_nugget", "inventory");

                String styleName = (style != NuggetStyle.IRON) ? style.name().toLowerCase() : getDefaultShapeForNugget(material);

                if (!styleName.equals("iron")) {
                    ResourceLocation targetPath = new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_nugget_" + styleName);
                    var baked = event.getModels().get(targetPath);
                    if (baked != null) event.getModels().put(itemLoc, baked);
                }
            }

            // 2. Raw Ore Model Overrides
            if (ModItems.RAW_ORES.containsKey(material)) {
                RawOreStyle rawStyle = ModConfig.getRawOreStyle(material);
                ModelResourceLocation itemLoc = new ModelResourceLocation(InfiniteResources.MOD_ID, "raw_" + material, "inventory");

                String styleName = (rawStyle != RawOreStyle.IRON) ? rawStyle.name().toLowerCase() : getDefaultShapeForRawOre(material);

                if (!styleName.equals("iron")) {
                    ResourceLocation targetPath = new ResourceLocation(InfiniteResources.MOD_ID, "item/raw_" + material + "_" + styleName);
                    var baked = event.getModels().get(targetPath);
                    if (baked != null) event.getModels().put(itemLoc, baked);
                }
            }

            // 3. Dynamic Gem Overrides
            if (ModItems.GEMS.containsKey(material)) {
                GemStyle gemStyle = ModConfig.getGemStyle(material);
                ModelResourceLocation itemLoc = new ModelResourceLocation(InfiniteResources.MOD_ID, material + "_gem", "inventory");

                String styleName = (gemStyle != GemStyle.DIAMOND) ? gemStyle.name().toLowerCase() : getDefaultShapeForGem(material);

                if (!styleName.equals("diamond")) {
                    ResourceLocation targetPath = new ResourceLocation(InfiniteResources.MOD_ID, "item/" + material + "_gem_" + styleName);
                    var baked = event.getModels().get(targetPath);
                    if (baked != null) event.getModels().put(itemLoc, baked);
                }
            }
        }

        // 4. FIXED STORAGE BLOCK MODEL OVERRIDES (BOTH IN-WORLD AND INVENTORY)
        ModBlocks.STORAGE_BLOCKS.keySet().forEach(material -> {
            boolean isGem = isGemstone(material);

            // Set up target keys for BOTH in-world configurations and inventory rendering contexts
            ModelResourceLocation blockWorldLoc = new ModelResourceLocation(InfiniteResources.MOD_ID, material + "_block", "");
            ModelResourceLocation blockItemLoc = new ModelResourceLocation(InfiniteResources.MOD_ID, material + "_block", "inventory");

            if (!isGem) {
                NuggetStyle metalStyle = ModConfig.getNuggetStyle(material);
                String styleName = (metalStyle == NuggetStyle.IRON) ? getDefaultMetalBlockStyle(material) : metalStyle.name().toLowerCase();

                if (!styleName.equals("iron")) {
                    ResourceLocation targetPath = new ResourceLocation(InfiniteResources.MOD_ID, "block/" + material + "_block_" + styleName);
                    var baked = event.getModels().get(targetPath);
                    if (baked != null) {
                        event.getModels().put(blockWorldLoc, baked);
                        event.getModels().put(blockItemLoc, baked); // Sync inventory display model
                    }
                }
            } else {
                GemStyle gemStyle = ModConfig.getGemStyle(material);
                String styleName = (gemStyle == GemStyle.DIAMOND) ? getDefaultGemBlockStyle(material) : gemStyle.name().toLowerCase();

                if (!styleName.equals("diamond")) {
                    ResourceLocation targetPath = new ResourceLocation(InfiniteResources.MOD_ID, "block/" + material + "_block_" + styleName);
                    var baked = event.getModels().get(targetPath);
                    if (baked != null) {
                        event.getModels().put(blockWorldLoc, baked);
                        event.getModels().put(blockItemLoc, baked); // Sync inventory display model
                    }
                }
            }
        });
    }

    private static String getDefaultShapeForNugget(String material) {
        switch (material) {
            case "gold": case "brass": case "bronze": case "electrum": case "ruby":
            case "topaz": case "citrine": case "amber": case "tigerseye": case "sunstone":
                return "gold";
            case "copper": case "lead": case "constantan": case "garnet": case "jasper":
            case "carnelian": case "amethyst": case "tanzanite": case "tourmaline": case "morganite":
                return "copper";
            default:
                return "iron";
        }
    }

    private static String getDefaultShapeForRawOre(String material) {
        switch (material) {
            case "silver": case "zinc": return "gold";
            case "aluminum": case "tin": return "copper";
            default: return "iron";
        }
    }

    private static String getDefaultShapeForGem(String material) {
        switch (material) {
            case "ruby": case "garnet": case "jasper": case "carnelian": return "emerald";
            case "sapphire": case "aquamarine": case "iolite": return "lapis";
            case "topaz": case "citrine": case "amber": case "tigerseye": return "quartz";
            case "amethyst": case "tanzanite": return "amethyst";
            default: return "diamond";
        }
    }

    private static boolean isGemstone(String material) {
        return material.equals("ruby") || material.equals("sapphire") || material.equals("topaz") || material.equals("amethyst") ||
                material.equals("opal") || material.equals("aquamarine") || material.equals("peridot") || material.equals("garnet") ||
                material.equals("jade") || material.equals("tourmaline") || material.equals("citrine") || material.equals("tanzanite") ||
                material.equals("amber") || material.equals("malachite") || material.equals("onyx") || material.equals("jasper") ||
                material.equals("agate") || material.equals("turquoise") || material.equals("tigerseye") || material.equals("moonstone") ||
                material.equals("sunstone") || material.equals("morganite") || material.equals("iolite") || material.equals("alexandrite") ||
                material.equals("carnelian");
    }

    private static String getDefaultMetalBlockStyle(String material) {
        if (material.equals("lead") || material.equals("silver") || material.equals("nickel") || material.equals("uranium")) return "gold";
        if (material.equals("steel") || material.equals("brass") || material.equals("bronze") || material.equals("invar") || material.equals("electrum") || material.equals("constantan")) return "copper";
        return "iron";
    }

    private static String getDefaultGemBlockStyle(String material) {
        if (material.equals("ruby") || material.equals("garnet") || material.equals("jasper") || material.equals("carnelian")) return "emerald";
        return "diamond";
    }
}