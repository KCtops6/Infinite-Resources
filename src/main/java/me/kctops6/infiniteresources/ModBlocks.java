package me.kctops6.infiniteresources;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, InfiniteResources.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InfiniteResources.MOD_ID);

    public static final Map<String, RegistryObject<Block>> STORAGE_BLOCKS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> STORAGE_BLOCK_ITEMS = new HashMap<>();

    static {
        // Metals & Alloys List (Matches the script)
        String[] metals = {"steel", "brass", "bronze", "tin", "lead", "silver", "nickel", "aluminum", "zinc", "invar", "electrum", "constantan", "uranium"};
        for (String mat : metals) {
            registerStorageBlock(mat, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK));
        }

        // Gemstones List (Matches the script)
        String[] gems = {"ruby", "sapphire", "topaz", "opal", "aquamarine", "peridot", "garnet", "jade", "tourmaline", "citrine", "tanzanite", "amber", "malachite", "onyx", "jasper", "agate", "turquoise", "tigerseye", "moonstone", "sunstone", "morganite", "iolite", "alexandrite", "carnelian"};
        for (String gem : gems) {
            registerStorageBlock(gem, BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK));
        }
    }

    private static void registerStorageBlock(String name, BlockBehaviour.Properties properties) {
        RegistryObject<Block> block = BLOCKS.register(name + "_block", () -> new Block(properties));
        STORAGE_BLOCKS.put(name, block);
        STORAGE_BLOCK_ITEMS.put(name, ITEMS.register(name + "_block", () -> new BlockItem(block.get(), new Item.Properties())));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }
}