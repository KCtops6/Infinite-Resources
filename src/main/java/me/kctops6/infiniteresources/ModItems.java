package me.kctops6.infiniteresources;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, InfiniteResources.MOD_ID);

    // Dynamic Item Maps
    public static final Map<String, RegistryObject<Item>> INGOTS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> NUGGETS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> DUSTS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> PLATES = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> RAW_ORES = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> GEMS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> RODS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> WIRES = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> GEARS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> DOUBLE_PLATES = new HashMap<>();

    // Individual Special Items
    public static final RegistryObject<Item> COAL_COKE = ITEMS.register("coal_coke",
            () -> new Item(new Item.Properties()));

    // Material Definitions
    public static final String[] MODDED_ORES = {
            "tin", "lead", "silver", "nickel", "aluminum", "zinc", "osmium", "uranium"
    };

    public static final String[] ALLOYS = {
            "steel", "brass", "bronze", "invar", "electrum", "constantan", "lumium", "signalum", "enderium"
    };

    public static final String[] GEMS_WITH_NUGGETS = {
            "diamond", "emerald", "lapis"
    };

    public static final String[] OTHER_VANILLA = {
            "iron", "gold", "copper", "obsidian", "coal"
    };

    public static final String[] MODDED_GEMS = {
            "ruby", "sapphire", "topaz", "opal", "aquamarine",
            "peridot", "garnet", "jade", "tourmaline", "citrine", "tanzanite",
            "amber", "malachite", "onyx", "jasper", "agate", "turquoise",
            "tigerseye", "moonstone", "sunstone", "morganite", "iolite", "alexandrite", "carnelian"
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
            INGOTS.put(material, ITEMS.register(material + "_ingot", () -> new Item(new Item.Properties())));
            NUGGETS.put(material, ITEMS.register(material + "_nugget", () -> new Item(new Item.Properties())));
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
            RAW_ORES.put(material, ITEMS.register("raw_" + material, () -> new Item(new Item.Properties())));
        }

        // 2. Crafted Alloys
        for (String material : ALLOYS) {
            INGOTS.put(material, ITEMS.register(material + "_ingot", () -> new Item(new Item.Properties())));
            NUGGETS.put(material, ITEMS.register(material + "_nugget", () -> new Item(new Item.Properties())));
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
        }

        // 3. Modded Gemstones
        for (String material : MODDED_GEMS) {
            GEMS.put(material, ITEMS.register(material + "_gem", () -> new Item(new Item.Properties())));
            NUGGETS.put(material, ITEMS.register(material + "_nugget", () -> new Item(new Item.Properties())));
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
        }

        // 4. Vanilla Core items
        for (String material : GEMS_WITH_NUGGETS) {
            NUGGETS.put(material, ITEMS.register(material + "_nugget", () -> new Item(new Item.Properties())));
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
            GEARS.put(material, ITEMS.register(material + "_gear", () -> new Item(new Item.Properties())));
        }

        for (String material : OTHER_VANILLA) {
            // Coal dust is registered here
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));

            // Skip plates and nuggets for coal
            if (!material.equals("coal")) {
                PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
            }
            if (material.equals("copper")) {
                NUGGETS.put(material, ITEMS.register(material + "_nugget", () -> new Item(new Item.Properties())));
            }
        }

        // 5. Rods, Wires, Gears, and Double Plates
        List<String> metalMaterials = new ArrayList<>();
        metalMaterials.addAll(List.of(MODDED_ORES));
        metalMaterials.addAll(List.of(ALLOYS));
        metalMaterials.addAll(List.of(OTHER_VANILLA));

        for (String material : metalMaterials) {
            // Skip rods, wires, gears, and double plates for coal
            if (material.equals("coal")) {
                continue;
            }

            RODS.put(material, ITEMS.register(material + "_rod", () -> new Item(new Item.Properties())));
            WIRES.put(material, ITEMS.register(material + "_wire", () -> new Item(new Item.Properties())));
            GEARS.put(material, ITEMS.register(material + "_gear", () -> new Item(new Item.Properties())));
            DOUBLE_PLATES.put(material, ITEMS.register("double_" + material + "_plate", () -> new Item(new Item.Properties())));
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}