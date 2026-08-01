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

    public static final RegistryObject<CreativeModeTab> GEMS_TAB = CREATIVE_MODE_TABS.register("infinite_gems",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GEMS.get("ruby").get()))
                    .title(Component.translatable("creativetab.infinite_gems"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.GEMS.containsKey(material)) {
                                output.accept(ModItems.GEMS.get(material).get());
                            }
                            if (ModBlocks.STORAGE_BLOCK_ITEMS.containsKey(material) && isGemstone(material)) {
                                output.accept(ModBlocks.STORAGE_BLOCK_ITEMS.get(material).get());
                            }
                        }
                    }).build());

    public static final RegistryObject<CreativeModeTab> INGOTS_TAB = CREATIVE_MODE_TABS.register("infinite_ingots",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.INGOTS.get("steel").get()))
                    .title(Component.translatable("creativetab.infinite_ingots"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.INGOTS.containsKey(material)) {
                                output.accept(ModItems.INGOTS.get(material).get());
                            }
                            if (ModBlocks.STORAGE_BLOCK_ITEMS.containsKey(material) && !isGemstone(material)) {
                                output.accept(ModBlocks.STORAGE_BLOCK_ITEMS.get(material).get());
                            }
                        }
                        output.accept(ModItems.COAL_COKE.get());
                        output.accept(ModBlocks.COAL_COKE_BLOCK_ITEM.get());
                    }).build());

    public static final RegistryObject<CreativeModeTab> NUGGETS_TAB = CREATIVE_MODE_TABS.register("infinite_nuggets",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.NUGGETS.get("steel").get()))
                    .title(Component.translatable("creativetab.infinite_nuggets"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.NUGGETS.containsKey(material)) {
                                output.accept(ModItems.NUGGETS.get(material).get());
                            }
                        }
                    }).build());

    public static final RegistryObject<CreativeModeTab> RAW_ORES_TAB = CREATIVE_MODE_TABS.register("infinite_raw_ores",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAW_ORES.get("tin").get()))
                    .title(Component.translatable("creativetab.infinite_raw_ores"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.RAW_ORES.containsKey(material)) {
                                output.accept(ModItems.RAW_ORES.get(material).get());
                            }
                        }
                    }).build());

    public static final RegistryObject<CreativeModeTab> DUSTS_TAB = CREATIVE_MODE_TABS.register("infinite_dusts",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DUSTS.get("steel").get()))
                    .title(Component.translatable("creativetab.infinite_dusts"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.DUSTS.containsKey(material)) {
                                output.accept(ModItems.DUSTS.get(material).get());
                            }
                        }
                    }).build());

    public static final RegistryObject<CreativeModeTab> PLATES_TAB = CREATIVE_MODE_TABS.register("infinite_plates",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PLATES.get("steel").get()))
                    .title(Component.translatable("creativetab.infinite_plates"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.PLATES.containsKey(material)) {
                                output.accept(ModItems.PLATES.get(material).get());
                            }
                        }
                    }).build());

    public static final RegistryObject<CreativeModeTab> RODS_TAB = CREATIVE_MODE_TABS.register("infinite_rods",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RODS.get("steel").get()))
                    .title(Component.translatable("creativetab.infinite_rods"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.RODS.containsKey(material)) {
                                output.accept(ModItems.RODS.get(material).get());
                            }
                        }
                    }).build());

    public static final RegistryObject<CreativeModeTab> WIRES_TAB = CREATIVE_MODE_TABS.register("infinite_wires",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.WIRES.get("steel").get()))
                    .title(Component.translatable("creativetab.infinite_wires"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.WIRES.containsKey(material)) {
                                output.accept(ModItems.WIRES.get(material).get());
                            }
                        }
                    }).build());

    public static final RegistryObject<CreativeModeTab> GEARS_TAB = CREATIVE_MODE_TABS.register("infinite_gears",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GEARS.get("steel").get()))
                    .title(Component.translatable("creativetab.infinite_gears"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.GEARS.containsKey(material)) {
                                output.accept(ModItems.GEARS.get(material).get());
                            }
                        }
                    }).build());

    public static final RegistryObject<CreativeModeTab> DOUBLE_PLATES_TAB = CREATIVE_MODE_TABS.register("infinite_double_plates",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DOUBLE_PLATES.get("steel").get()))
                    .title(Component.translatable("creativetab.infinite_double_plates"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.DOUBLE_PLATES.containsKey(material)) {
                                output.accept(ModItems.DOUBLE_PLATES.get(material).get());
                            }
                        }
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    private static boolean isGemstone(String material) {
        for (String gem : ModItems.MODDED_GEMS) {
            if (gem.equals(material)) return true;
        }
        return false;
    }
}