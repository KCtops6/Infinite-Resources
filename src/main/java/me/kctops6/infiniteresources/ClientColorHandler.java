package me.kctops6.infiniteresources;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = InfiniteResources.MOD_ID, value = Dist.CLIENT)
public class ClientColorHandler {

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        for (String material : ModItems.MATERIALS) {
            int color = MaterialColors.COLORS.getOrDefault(material, 0xFFFFFF);

            registerIfPresent(event, ModItems.INGOTS.get(material), color);
            registerIfPresent(event, ModItems.DUSTS.get(material), color);
            registerIfPresent(event, ModItems.PLATES.get(material), color);
            registerIfPresent(event, ModItems.NUGGETS.get(material), color);
            registerIfPresent(event, ModItems.RAW_ORES.get(material), color);
            registerIfPresent(event, ModItems.GEMS.get(material), color);
            registerIfPresent(event, ModItems.RODS.get(material), color);
            registerIfPresent(event, ModItems.WIRES.get(material), color);
            registerIfPresent(event, ModItems.GEARS.get(material), color);
            registerIfPresent(event, ModItems.DOUBLE_PLATES.get(material), color);
        }

            registerIfPresent(event, ModBlocks.COAL_COKE_BLOCK_ITEM, MaterialColors.COLORS.get("coal"));
            ModBlocks.STORAGE_BLOCK_ITEMS.forEach((material, item) ->
                registerIfPresent(event, item, MaterialColors.COLORS.getOrDefault(material, 0xFFFFFF)));
            ModBlocks.RAW_STORAGE_BLOCK_ITEMS.forEach((material, item) ->
                registerIfPresent(event, item, MaterialColors.COLORS.getOrDefault(material, 0xFFFFFF)));
    }

            @SubscribeEvent
            public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
            event.register((state, level, pos, tintIndex) -> MaterialColors.COLORS.getOrDefault("coal", 0xFFFFFF),
                ModBlocks.COAL_COKE_BLOCK.get());
            ModBlocks.STORAGE_BLOCKS.forEach((material, block) ->
                event.register((state, level, pos, tintIndex) ->
                    MaterialColors.COLORS.getOrDefault(material, 0xFFFFFF), block.get()));
            ModBlocks.RAW_STORAGE_BLOCKS.forEach((material, block) ->
                event.register((state, level, pos, tintIndex) ->
                    MaterialColors.COLORS.getOrDefault(material, 0xFFFFFF), block.get()));
            }

    private static void registerIfPresent(RegisterColorHandlersEvent.Item event, net.neoforged.neoforge.registries.DeferredItem<?> itemHolder, int color) {
        if (itemHolder != null && itemHolder.isBound()) {
            // Always return color for layer 0 or default
            event.register((stack, tintIndex) -> (tintIndex == 0 || tintIndex == -1) ? color : 0xFFFFFFFF, itemHolder.get());
        }
    }
}