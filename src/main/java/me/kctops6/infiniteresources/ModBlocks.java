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
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, InfiniteResources.MOD_ID);

    public static final Map<String, RegistryObject<Block>> STORAGE_BLOCKS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> STORAGE_BLOCK_ITEMS = new HashMap<>();

    public static final Map<String, RegistryObject<Block>> RAW_STORAGE_BLOCKS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> RAW_STORAGE_BLOCK_ITEMS = new HashMap<>();

    // Coal Coke Block
    public static final RegistryObject<Block> COAL_COKE_BLOCK = BLOCKS.register("coal_coke_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK)));

    public static final RegistryObject<Item> COAL_COKE_BLOCK_ITEM = ModItems.ITEMS.register("coal_coke_block",
            () -> new BlockItem(COAL_COKE_BLOCK.get(), new Item.Properties()));

    static {
        // 1. Modded Ores (Standard blocks + Raw blocks)
        for (String material : ModItems.MODDED_ORES) {
            // Storage Block
            RegistryObject<Block> block = BLOCKS.register(material + "_block",
                    () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
            STORAGE_BLOCKS.put(material, block);
            STORAGE_BLOCK_ITEMS.put(material, ModItems.ITEMS.register(material + "_block",
                    () -> new BlockItem(block.get(), new Item.Properties())));

            // Raw Ore Block
            RegistryObject<Block> rawBlock = BLOCKS.register("raw_" + material + "_block",
                    () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
            RAW_STORAGE_BLOCKS.put(material, rawBlock);
            RAW_STORAGE_BLOCK_ITEMS.put(material, ModItems.ITEMS.register("raw_" + material + "_block",
                    () -> new BlockItem(rawBlock.get(), new Item.Properties())));
        }

        // 2. Alloys
        for (String material : ModItems.ALLOYS) {
            RegistryObject<Block> block = BLOCKS.register(material + "_block",
                    () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
            STORAGE_BLOCKS.put(material, block);
            STORAGE_BLOCK_ITEMS.put(material, ModItems.ITEMS.register(material + "_block",
                    () -> new BlockItem(block.get(), new Item.Properties())));
        }

        // 3. Modded Gems
        for (String material : ModItems.MODDED_GEMS) {
            RegistryObject<Block> block = BLOCKS.register(material + "_block",
                    () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));
            STORAGE_BLOCKS.put(material, block);
            STORAGE_BLOCK_ITEMS.put(material, ModItems.ITEMS.register(material + "_block",
                    () -> new BlockItem(block.get(), new Item.Properties())));
        }
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}