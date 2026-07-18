package me.kctops6.infiniteresources;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(InfiniteResources.MOD_ID)
public class InfiniteResources {
    public static final String MOD_ID = "infinite_resources";

    public InfiniteResources() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // 1. Tell Forge to look out for your configuration file
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, me.kctops6.infiniteresources.ModConfig.SPEC);

        // 2. Attach the new listener that handles configuration data readiness flags
        modEventBus.addListener(me.kctops6.infiniteresources.ModConfig::onConfigLoad);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}