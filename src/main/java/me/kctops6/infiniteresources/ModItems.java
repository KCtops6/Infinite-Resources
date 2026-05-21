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

    public static final String[] MODDED_MATERIALS = {
            "steel", "brass", "bronze", "tin", "lead", "silver",
            "nickel", "aluminum", "zinc", "invar", "electrum", "constantan"
    };

    // Explicit list for vanilla gems that you want to have custom nuggets for
    public static final String[] GEMS_WITH_NUGGETS = {
            "diamond", "emerald", "lapis"
    };

    // Remaining vanilla items that only get dusts and plates (iron and gold already have vanilla nuggets)
    public static final String[] OTHER_VANILLA = {
            "iron", "gold"
    };

    public static final List<String> MATERIALS = new ArrayList<>();

    static {
        MATERIALS.addAll(List.of(OTHER_VANILLA));
        MATERIALS.addAll(List.of(GEMS_WITH_NUGGETS));
        MATERIALS.addAll(List.of(MODDED_MATERIALS));
        for (String material : OTHER_VANILLA) {
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
        }
        for (String material : GEMS_WITH_NUGGETS) {
            NUGGETS.put(material, ITEMS.register(material + "_nugget", () -> new Item(new Item.Properties())));
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
        }
        for (String material : MODDED_MATERIALS) {
            INGOTS.put(material, ITEMS.register(material + "_ingot", () -> new Item(new Item.Properties())));
            NUGGETS.put(material, ITEMS.register(material + "_nugget", () -> new Item(new Item.Properties())));
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}