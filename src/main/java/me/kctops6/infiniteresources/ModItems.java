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

    // 1. Modded metals that are found naturally as ores in the ground
    public static final String[] MODDED_ORES = {
            "tin", "lead", "silver", "nickel", "aluminum", "zinc"
    };

    // 2. Modded alloys that are crafted/blended (They DO NOT get raw ore items)
    public static final String[] ALLOYS = {
            "steel", "brass", "bronze", "invar", "electrum", "constantan"
    };

    public static final String[] GEMS_WITH_NUGGETS = {
            "diamond", "emerald", "lapis"
    };

    public static final String[] OTHER_VANILLA = {
            "iron", "gold"
    };

    public static final List<String> MATERIALS = new ArrayList<>();

    static {
        // Build master list tracking order
        MATERIALS.addAll(List.of(MODDED_ORES));
        MATERIALS.addAll(List.of(ALLOYS));
        MATERIALS.addAll(List.of(GEMS_WITH_NUGGETS));
        MATERIALS.addAll(List.of(OTHER_VANILLA));

        // Register standard forms for ALL modded metals (Ores + Alloys)
        for (String material : MODDED_ORES) {
            INGOTS.put(material, ITEMS.register(material + "_ingot", () -> new Item(new Item.Properties())));
            NUGGETS.put(material, ITEMS.register(material + "_nugget", () -> new Item(new Item.Properties())));
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
            // Registers raw ore items ONLY for natural ground ores
            RAW_ORES.put(material, ITEMS.register("raw_" + material, () -> new Item(new Item.Properties())));
        }

        for (String material : ALLOYS) {
            INGOTS.put(material, ITEMS.register(material + "_ingot", () -> new Item(new Item.Properties())));
            NUGGETS.put(material, ITEMS.register(material + "_nugget", () -> new Item(new Item.Properties())));
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
            // Notice: raw_brass, raw_steel, etc., are completely skipped here!
        }

        // Vanilla Gems: get custom Nuggets, Dusts, and Plates
        for (String material : GEMS_WITH_NUGGETS) {
            NUGGETS.put(material, ITEMS.register(material + "_nugget", () -> new Item(new Item.Properties())));
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
        }

        // Regular Vanilla: get only Dusts and Plates
        for (String material : OTHER_VANILLA) {
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}