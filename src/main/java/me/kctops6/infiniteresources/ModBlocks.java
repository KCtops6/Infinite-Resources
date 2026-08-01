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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, InfiniteResources.MOD_ID);

    public static final Map<String, RegistryObject<Block>> STORAGE_BLOCKS = new HashMap<>();
    public static final Map<String, RegistryObject<Item>> STORAGE_BLOCK_ITEMS = new HashMap<>();

    // Coal Coke Block
    public static final RegistryObject<Block> COAL_COKE_BLOCK = BLOCKS.register("coal_coke_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK)));

    public static final RegistryObject<Item> COAL_COKE_BLOCK_ITEM = ModItems.ITEMS.register("coal_coke_block",
            () -> new BlockItem(COAL_COKE_BLOCK.get(), new Item.Properties()));

    static {
        // Register Storage Blocks for Metals and Alloys (Iron Properties)
        List<String> metalsAndAlloys = new ArrayList<>();
        metalsAndAlloys.addAll(List.of(ModItems.MODDED_ORES));
        metalsAndAlloys.addAll(List.of(ModItems.ALLOYS));

        for (String material : metalsAndAlloys) {
            RegistryObject<Block> block = BLOCKS.register(material + "_block",
                    () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
            STORAGE_BLOCKS.put(material, block);

            RegistryObject<Item> blockItem = ModItems.ITEMS.register(material + "_block",
                    () -> new BlockItem(block.get(), new Item.Properties()));
            STORAGE_BLOCK_ITEMS.put(material, blockItem);
        }

        // Register Storage Blocks for Gemstones (Diamond Properties)
        for (String material : ModItems.MODDED_GEMS) {
            RegistryObject<Block> block = BLOCKS.register(material + "_block",
                    () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));
            STORAGE_BLOCKS.put(material, block);

            RegistryObject<Item> blockItem = ModItems.ITEMS.register(material + "_block",
                    () -> new BlockItem(block.get(), new Item.Properties()));
            STORAGE_BLOCK_ITEMS.put(material, blockItem);
        }
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}