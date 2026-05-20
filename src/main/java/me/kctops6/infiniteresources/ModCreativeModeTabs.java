package me.kctops6.infiniteresources;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, InfiniteResources.MOD_ID);
    public static final RegistryObject<CreativeModeTab> INGOTS_TAB = CREATIVE_MODE_TABS.register("infinite_ingots",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.INGOTS.get("steel").get()))
                    .title(Component.translatable("creativetab.infinite_ingots"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.INGOTS.containsKey(material)) output.accept(ModItems.INGOTS.get(material).get());
                        }
                    }).build());
    public static final RegistryObject<CreativeModeTab> NUGGETS_TAB = CREATIVE_MODE_TABS.register("infinite_nuggets",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.NUGGETS.get("steel").get()))
                    .title(Component.translatable("creativetab.infinite_nuggets"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.NUGGETS.containsKey(material)) output.accept(ModItems.NUGGETS.get(material).get());
                        }
                    }).build());
    public static final RegistryObject<CreativeModeTab> DUSTS_TAB = CREATIVE_MODE_TABS.register("infinite_dusts",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DUSTS.get("steel").get()))
                    .title(Component.translatable("creativetab.infinite_dusts"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.DUSTS.containsKey(material)) output.accept(ModItems.DUSTS.get(material).get());
                        }
                    }).build());
    public static final RegistryObject<CreativeModeTab> PLATES_TAB = CREATIVE_MODE_TABS.register("infinite_plates",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PLATES.get("steel").get()))
                    .title(Component.translatable("creativetab.infinite_plates"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.PLATES.containsKey(material)) output.accept(ModItems.PLATES.get(material).get());
                        }
                    }).build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}