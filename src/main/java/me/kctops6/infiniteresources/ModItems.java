package me.kctops6.infiniteresources;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(InfiniteResources.MOD_ID);

    // Dynamic Item Maps using DeferredItem
    public static final Map<String, DeferredItem<Item>> INGOTS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> NUGGETS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> DUSTS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> PLATES = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> RAW_ORES = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> GEMS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> RODS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> WIRES = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> GEARS = new HashMap<>();
    public static final Map<String, DeferredItem<Item>> DOUBLE_PLATES = new HashMap<>();

    // Individual Special Items
    public static final DeferredItem<Item> COAL_COKE = ITEMS.registerSimpleItem("coal_coke", new Item.Properties());
    public static final DeferredItem<Item> SILICON = ITEMS.registerSimpleItem("silicon", new Item.Properties());

    // Material Definitions
    public static final String[] MODDED_ORES = {
            "tin", "lead", "silver", "nickel", "aluminum", "zinc", "osmium", "uranium", "platinum"
    };

    public static final String[] ALLOYS = {
            "steel", "brass", "bronze", "invar", "electrum", "constantan", "lumium", "signalum", "enderium"
    };

    public static final String[] GEMS_WITH_NUGGETS = {
            "diamond", "emerald", "lapis"
    };

    public static final String[] OTHER_VANILLA = {
            "iron", "gold", "copper", "obsidian", "coal", "charcoal", "netherite", "ender_pearl", "quartz"
    };

    public static final String[] MODDED_GEMS = {
            "ruby", "sapphire", "topaz", "opal", "aquamarine",
            "peridot", "garnet", "jade", "tourmaline", "citrine", "tanzanite",
            "amber", "malachite", "onyx", "jasper", "agate", "turquoise",
            "tigerseye", "moonstone", "sunstone", "morganite", "iolite", "alexandrite", "carnelian",
            "apatite", "cinnabar", "niter", "sulfur"
    };

    public static final List<String> MATERIALS = new ArrayList<>();

    static {
        MATERIALS.addAll(List.of(MODDED_ORES));
        MATERIALS.addAll(List.of(ALLOYS));
        MATERIALS.addAll(List.of(MODDED_GEMS));
        MATERIALS.addAll(List.of(GEMS_WITH_NUGGETS));
        MATERIALS.addAll(List.of(OTHER_VANILLA));

        // 1. Natural Ores
        for (String material : MODDED_ORES) {
            INGOTS.put(material, ITEMS.registerSimpleItem(material + "_ingot", new Item.Properties()));
            NUGGETS.put(material, ITEMS.registerSimpleItem(material + "_nugget", new Item.Properties()));
            DUSTS.put(material, ITEMS.registerSimpleItem(material + "_dust", new Item.Properties()));
            PLATES.put(material, ITEMS.registerSimpleItem(material + "_plate", new Item.Properties()));
            RAW_ORES.put(material, ITEMS.registerSimpleItem("raw_" + material, new Item.Properties()));
        }

        // 2. Crafted Alloys
        for (String material : ALLOYS) {
            INGOTS.put(material, ITEMS.registerSimpleItem(material + "_ingot", new Item.Properties()));
            NUGGETS.put(material, ITEMS.registerSimpleItem(material + "_nugget", new Item.Properties()));
            DUSTS.put(material, ITEMS.registerSimpleItem(material + "_dust", new Item.Properties()));
            PLATES.put(material, ITEMS.registerSimpleItem(material + "_plate", new Item.Properties()));
        }

        // 3. Modded Gemstones
        for (String material : MODDED_GEMS) {
            GEMS.put(material, ITEMS.registerSimpleItem(material + "_gem", new Item.Properties()));
            NUGGETS.put(material, ITEMS.registerSimpleItem(material + "_nugget", new Item.Properties()));
            DUSTS.put(material, ITEMS.registerSimpleItem(material + "_dust", new Item.Properties()));
            PLATES.put(material, ITEMS.registerSimpleItem(material + "_plate", new Item.Properties()));
        }

        // 4. Vanilla Core items
        for (String material : GEMS_WITH_NUGGETS) {
            NUGGETS.put(material, ITEMS.registerSimpleItem(material + "_nugget", new Item.Properties()));
            DUSTS.put(material, ITEMS.registerSimpleItem(material + "_dust", new Item.Properties()));
            PLATES.put(material, ITEMS.registerSimpleItem(material + "_plate", new Item.Properties()));
            GEARS.put(material, ITEMS.registerSimpleItem(material + "_gear", new Item.Properties()));
        }

        for (String material : OTHER_VANILLA) {
            DUSTS.put(material, ITEMS.registerSimpleItem(material + "_dust", new Item.Properties()));

            if (!material.equals("coal") && !material.equals("charcoal") && !material.equals("ender_pearl") && !material.equals("quartz")) {
                PLATES.put(material, ITEMS.registerSimpleItem(material + "_plate", new Item.Properties()));
            }
            if (material.equals("copper") || material.equals("netherite")) {
                NUGGETS.put(material, ITEMS.registerSimpleItem(material + "_nugget", new Item.Properties()));
            }
        }

        List<String> metalMaterials = new ArrayList<>();
        metalMaterials.addAll(List.of(MODDED_ORES));
        metalMaterials.addAll(List.of(ALLOYS));
        metalMaterials.addAll(List.of(OTHER_VANILLA));

        for (String material : metalMaterials) {
            if (material.equals("coal") || material.equals("charcoal") || material.equals("ender_pearl") || material.equals("quartz")) continue;
            RODS.put(material, ITEMS.registerSimpleItem(material + "_rod", new Item.Properties()));
            WIRES.put(material, ITEMS.registerSimpleItem(material + "_wire", new Item.Properties()));
            GEARS.put(material, ITEMS.registerSimpleItem(material + "_gear", new Item.Properties()));
            DOUBLE_PLATES.put(material, ITEMS.registerSimpleItem("double_" + material + "_plate", new Item.Properties()));
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}