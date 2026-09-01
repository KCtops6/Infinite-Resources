package me.kctops6.infiniteresources;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InfiniteResources.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientColorHandler {

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        for (String material : ModItems.MATERIALS) {
            int color = MaterialColors.COLORS.getOrDefault(material, 0xFFFFFF);

            if (ModItems.INGOTS.containsKey(material)) {
                event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, ModItems.INGOTS.get(material).get());
            }
            if (ModItems.DUSTS.containsKey(material)) {
                event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, ModItems.DUSTS.get(material).get());
            }
            if (ModItems.PLATES.containsKey(material)) {
                event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, ModItems.PLATES.get(material).get());
            }
            if (ModItems.NUGGETS.containsKey(material)) {
                event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, ModItems.NUGGETS.get(material).get());
            }
            if (ModItems.RAW_ORES.containsKey(material)) {
                event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, ModItems.RAW_ORES.get(material).get());
            }
            if (ModItems.GEMS.containsKey(material)) {
                event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, ModItems.GEMS.get(material).get());
            }
            if (ModItems.RODS.containsKey(material)) {
                event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, ModItems.RODS.get(material).get());
            }
            if (ModItems.WIRES.containsKey(material)) {
                event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, ModItems.WIRES.get(material).get());
            }
            if (ModItems.GEARS.containsKey(material)) {
                event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, ModItems.GEARS.get(material).get());
            }
            if (ModItems.DOUBLE_PLATES.containsKey(material)) {
                event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, ModItems.DOUBLE_PLATES.get(material).get());
            }
        }

        // Standard Storage Block Items
        ModBlocks.STORAGE_BLOCK_ITEMS.forEach((material, itemObj) -> {
            int color = MaterialColors.COLORS.getOrDefault(material, 0xFFFFFF);
            event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, itemObj.get());
        });

        // Raw Ore Storage Block Items
        ModBlocks.RAW_STORAGE_BLOCK_ITEMS.forEach((material, itemObj) -> {
            int color = MaterialColors.COLORS.getOrDefault(material, 0xFFFFFF);
            event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, itemObj.get());
        });
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        // Standard Storage Blocks
        ModBlocks.STORAGE_BLOCKS.forEach((material, blockObj) -> {
            int color = MaterialColors.COLORS.getOrDefault(material, 0xFFFFFF);
            event.register((state, world, pos, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, blockObj.get());
        });

        // Raw Ore Storage Blocks
        ModBlocks.RAW_STORAGE_BLOCKS.forEach((material, blockObj) -> {
            int color = MaterialColors.COLORS.getOrDefault(material, 0xFFFFFF);
            event.register((state, world, pos, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, blockObj.get());
        });
    }
}