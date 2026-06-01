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

    public static final Map<String, RegistryObject<Item>> INGOTS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> NUGGETS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> DUSTS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> PLATES = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> RAW_ORES = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> GEMS = new HashMap<>();

    public static final String[] MODDED_ORES = {
            "tin", "lead", "silver", "nickel", "aluminum", "zinc"
    };

    public static final String[] ALLOYS = {
            "steel", "brass", "bronze", "invar", "electrum", "constantan"
    };

    public static final String[] GEMS_WITH_NUGGETS = {
            "diamond", "emerald", "lapis"
    };

    public static final String[] OTHER_VANILLA = {
            "iron", "gold", "copper", "obsidian"
    };

    // Exactly 25 standardized modded gemstone definitions
    public static final String[] MODDED_GEMS = {
            "ruby", "sapphire", "topaz", "amethyst", "opal", "aquamarine",
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

        // 2. Crafted Blends
        for (String material : ALLOYS) {
            INGOTS.put(material, ITEMS.register(material + "_ingot", () -> new Item(new Item.Properties())));
            NUGGETS.put(material, ITEMS.register(material + "_nugget", () -> new Item(new Item.Properties())));
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
        }

        // 3. Modded Gemstones (Gets gem form, custom nuggets, dusts, and plates)
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
        }

        for (String material : OTHER_VANILLA) {
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
            if (material.equals("copper")) NUGGETS.put(material, ITEMS.register(material + "_nugget", () -> new Item(new Item.Properties())));
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}