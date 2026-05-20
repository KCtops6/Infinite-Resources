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
    public static final String[] VANILLA_MATERIALS = {
            "iron", "gold", "diamond", "emerald", "lapis"
    };
    public static final List<String> MATERIALS = new ArrayList<>();
    static {
        MATERIALS.addAll(List.of(MODDED_MATERIALS));
        MATERIALS.addAll(List.of(VANILLA_MATERIALS));
        for (String material : MODDED_MATERIALS) {
            INGOTS.put(material, ITEMS.register(material + "_ingot", () -> new Item(new Item.Properties())));
            NUGGETS.put(material, ITEMS.register(material + "_nugget", () -> new Item(new Item.Properties())));
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
        }
        for (String material : VANILLA_MATERIALS) {
            DUSTS.put(material, ITEMS.register(material + "_dust", () -> new Item(new Item.Properties())));
            PLATES.put(material, ITEMS.register(material + "_plate", () -> new Item(new Item.Properties())));
        }
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}