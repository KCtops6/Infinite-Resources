package me.kctops6.infiniteresources;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;

@EventBusSubscriber(modid = InfiniteResources.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientModelHandler {

    @SubscribeEvent
    public static void onRegisterAdditionalModels(ModelEvent.RegisterAdditional event) {
        // Nuggets
        registerTemplate(event, "template_nugget_gold");
        registerTemplate(event, "template_nugget_copper");
        registerTemplate(event, "template_nugget_iron");

        // Raw Ores
        registerTemplate(event, "template_raw_gold");
        registerTemplate(event, "template_raw_copper");
        registerTemplate(event, "template_raw_iron");

        // Gems
        registerTemplate(event, "template_gem_diamond");
        registerTemplate(event, "template_gem_emerald");
        registerTemplate(event, "template_gem_quartz");
        registerTemplate(event, "template_gem_amethyst");
        registerTemplate(event, "template_gem_lapis");

        // Other Item Templates
        registerTemplate(event, "template_ingot");
        registerTemplate(event, "template_gear");
        registerTemplate(event, "template_plate");
        registerTemplate(event, "template_double_plate");
        registerTemplate(event, "template_dust");
        registerTemplate(event, "template_rod");
        registerTemplate(event, "template_wire");

        // Block Templates
        registerBlockTemplate(event, "template_block_iron");
        registerBlockTemplate(event, "template_block_gold");
        registerBlockTemplate(event, "template_block_copper");
        registerBlockTemplate(event, "template_block_diamond");
        registerBlockTemplate(event, "template_block_emerald");
        registerBlockTemplate(event, "template_block_lapis");
        registerBlockTemplate(event, "template_block_amethyst");
        registerBlockTemplate(event, "template_block_coal");
        registerBlockTemplate(event, "template_block_netherite");
        registerBlockTemplate(event, "template_block_quartz");
        registerBlockTemplate(event, "template_block_redstone");
    }

    private static void registerTemplate(ModelEvent.RegisterAdditional event, String name) {
        event.register(ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(InfiniteResources.MOD_ID, "item/" + name)
        ));
    }

    private static void registerBlockTemplate(ModelEvent.RegisterAdditional event, String name) {
        event.register(ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(InfiniteResources.MOD_ID, "block/" + name)
        ));
    }

    @SubscribeEvent
    public static void onModifyBakingResult(ModelEvent.ModifyBakingResult event) {
        for (String material : ModItems.MATERIALS) {
            // Ingots
            if (ModItems.INGOTS.containsKey(material)) {
                swapItemModel(event, material + "_ingot", "template_ingot");
            }

            // Nuggets
            if (ModItems.NUGGETS.containsKey(material)) {
                NuggetStyle style = ModConfig.getNuggetStyle(material);
                String styleName = (style != NuggetStyle.IRON) ? style.name().toLowerCase() : getDefaultShapeForNugget(material);
                swapItemModel(event, material + "_nugget", "template_nugget_" + styleName);
            }

            // Raw Ores
            if (ModItems.RAW_ORES.containsKey(material)) {
                RawOreStyle rawStyle = ModConfig.getRawOreStyle(material);
                String styleName = (rawStyle != RawOreStyle.IRON) ? rawStyle.name().toLowerCase() : getDefaultShapeForRawOre(material);
                swapItemModel(event, "raw_" + material, "template_raw_" + styleName);
            }

            // Gems
            if (ModItems.GEMS.containsKey(material)) {
                GemStyle gemStyle = ModConfig.getGemStyle(material);
                String styleName = (gemStyle != GemStyle.DIAMOND) ? gemStyle.name().toLowerCase() : getDefaultShapeForGem(material);
                swapItemModel(event, material + "_gem", "template_gem_" + styleName);
            }

            // Gears
            if (ModItems.GEARS.containsKey(material)) {
                swapItemModel(event, material + "_gear", "template_gear");
            }

            // Plates
            if (ModItems.PLATES.containsKey(material)) {
                swapItemModel(event, material + "_plate", "template_plate");
            }

            // Double Plates
            if (ModItems.DOUBLE_PLATES.containsKey(material)) {
                swapItemModel(event, "double_" + material + "_plate", "template_double_plate");
            }

            // Dusts
            if (ModItems.DUSTS.containsKey(material)) {
                swapItemModel(event, material + "_dust", "template_dust");
            }

            // Rods
            if (ModItems.RODS.containsKey(material)) {
                swapItemModel(event, material + "_rod", "template_rod");
            }

            // Wires
            if (ModItems.WIRES.containsKey(material)) {
                swapItemModel(event, material + "_wire", "template_wire");
            }

            // Storage Blocks & Items
            if (ModBlocks.STORAGE_BLOCKS.containsKey(material)) {
                String blockTemplate = getBlockTemplateForMaterial(material);
                swapBlockAndItemModel(event, material + "_block", blockTemplate);
            }

            // Raw Storage Blocks & Items
            if (ModBlocks.RAW_STORAGE_BLOCKS.containsKey(material)) {
                swapBlockAndItemModel(event, "raw_" + material + "_block", "template_block_iron");
            }
        }

        // Coal Coke Block
        swapBlockAndItemModel(event, "coal_coke_block", "template_block_coal");
    }

    private static void swapItemModel(ModelEvent.ModifyBakingResult event, String itemName, String templateName) {
        ModelResourceLocation itemLoc = ModelResourceLocation.inventory(
                ResourceLocation.fromNamespaceAndPath(InfiniteResources.MOD_ID, itemName)
        );
        ModelResourceLocation targetPath = ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(InfiniteResources.MOD_ID, "item/" + templateName)
        );

        swapModel(event, itemLoc, targetPath);
    }

    private static void swapBlockAndItemModel(ModelEvent.ModifyBakingResult event, String blockName, String templateName) {
        // 1. World Block Location (variants usually default to empty variant string or normal)
        ModelResourceLocation blockWorldLoc = new ModelResourceLocation(
                ResourceLocation.fromNamespaceAndPath(InfiniteResources.MOD_ID, blockName), ""
        );
        // 2. Inventory Block Item Location
        ModelResourceLocation itemInvLoc = ModelResourceLocation.inventory(
                ResourceLocation.fromNamespaceAndPath(InfiniteResources.MOD_ID, blockName)
        );

        // 3. Registered Additional Standalone Target Template
        ModelResourceLocation targetPath = ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(InfiniteResources.MOD_ID, "block/" + templateName)
        );

        swapModel(event, blockWorldLoc, targetPath);
        swapModel(event, itemInvLoc, targetPath);
    }

    private static void swapModel(ModelEvent.ModifyBakingResult event, ModelResourceLocation original, ModelResourceLocation target) {
        var baked = event.getModels().get(target);
        if (baked != null) {
            event.getModels().put(original, baked);
        } else {
            // Check fallback for standalone path resolution
            var fallbackBaked = event.getModels().get(ModelResourceLocation.standalone(target.id()));
            if (fallbackBaked != null) {
                event.getModels().put(original, fallbackBaked);
            } else {
                System.err.println("[" + InfiniteResources.MOD_ID + "] Missing model definition in registry: " + target);
            }
        }
    }

    private static String getBlockTemplateForMaterial(String material) {
        return switch (material) {
            case "amethyst", "tanzanite", "iolite" -> "template_block_amethyst";
            case "coal" -> "template_block_coal";
            case "copper", "bronze", "constantan" -> "template_block_copper";
            case "diamond", "aquamarine", "opal" -> "template_block_diamond";
            case "emerald", "peridot", "jade", "malachite" -> "template_block_emerald";
            case "gold", "brass", "electrum", "topaz", "citrine", "amber" -> "template_block_gold";
            case "lapis", "sapphire" -> "template_block_lapis";
            case "netherite", "onyx", "obsidian" -> "template_block_netherite";
            default -> "template_block_iron";
        };
    }

    private static String getDefaultShapeForNugget(String material) {
        return switch (material) {
            case "gold", "brass", "bronze", "electrum", "ruby", "topaz", "citrine", "amber", "tigerseye", "sunstone" ->
                    "gold";
            case "copper", "lead", "constantan", "garnet", "jasper", "carnelian", "amethyst", "tanzanite", "tourmaline",
                 "morganite" -> "copper";
            default -> "iron";
        };
    }

    private static String getDefaultShapeForRawOre(String material) {
        return switch (material) {
            case "silver", "zinc" -> "gold";
            case "aluminum", "tin" -> "copper";
            default -> "iron";
        };
    }

    private static String getDefaultShapeForGem(String material) {
        return switch (material) {
            case "ruby", "garnet", "jasper", "carnelian" -> "emerald";
            case "sapphire", "aquamarine", "iolite" -> "lapis";
            case "topaz", "citrine", "amber", "tigerseye" -> "quartz";
            case "amethyst", "tanzanite" -> "amethyst";
            default -> "diamond";
        };
    }
}