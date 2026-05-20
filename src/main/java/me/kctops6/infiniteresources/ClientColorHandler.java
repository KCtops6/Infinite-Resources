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

            // Clean lookups using map boundary checks to respect your vanilla omissions safely
            if (ModItems.INGOTS.containsKey(material)) {
                event.register((stack, tintIndex) -> color, ModItems.INGOTS.get(material).get());
            }
            if (ModItems.NUGGETS.containsKey(material)) {
                event.register((stack, tintIndex) -> color, ModItems.NUGGETS.get(material).get());
            }
            if (ModItems.DUSTS.containsKey(material)) {
                event.register((stack, tintIndex) -> color, ModItems.DUSTS.get(material).get());
            }
            if (ModItems.PLATES.containsKey(material)) {
                event.register((stack, tintIndex) -> color, ModItems.PLATES.get(material).get());
            }
        }
    }
}