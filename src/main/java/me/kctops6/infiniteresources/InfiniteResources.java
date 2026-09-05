package me.kctops6.infiniteresources;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(InfiniteResources.MOD_ID)
public class InfiniteResources {
    public static final String MOD_ID = "infinite_resources";

    public InfiniteResources(IEventBus modEventBus, ModContainer modContainer) {
        // 1. Register configuration file directly via ModContainer
        modContainer.registerConfig(ModConfig.Type.COMMON, me.kctops6.infiniteresources.ModConfig.SPEC);

        // 2. Attach configuration listener
        modEventBus.addListener(me.kctops6.infiniteresources.ModConfig::onConfigLoad);

        // Register registries on mod event bus
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
    }
}