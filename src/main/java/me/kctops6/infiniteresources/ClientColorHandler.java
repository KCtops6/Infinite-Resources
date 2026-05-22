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
            // Add dynamic uniform tinting for your new Raw Ores maps
            if (ModItems.RAW_ORES.containsKey(material)) {
                event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF, ModItems.RAW_ORES.get(material).get());
            }
        }
    }
}