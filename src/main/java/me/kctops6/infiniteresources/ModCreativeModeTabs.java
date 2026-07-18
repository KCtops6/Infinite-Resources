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

    // 1. DEDICATED STORAGE BLOCKS TAB
    public static final RegistryObject<CreativeModeTab> STORAGE_BLOCKS_TAB = CREATIVE_MODE_TABS.register("infinite_storage_blocks",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.STORAGE_BLOCK_ITEMS.get("steel").get())) // Uses Steel Block as the icon
                    .title(Component.translatable("creativetab.infinite_storage_blocks"))
                    .displayItems((parameters, output) -> {
                        // Automatically populates all registered storage blocks in your system loop
                        for (String material : ModItems.MATERIALS) {
                            if (ModBlocks.STORAGE_BLOCK_ITEMS.containsKey(material)) {
                                output.accept(ModBlocks.STORAGE_BLOCK_ITEMS.get(material).get());
                            }
                        }
                    }).build());

    // 2. CLEAN GEMS TAB (Only items now)
    public static final RegistryObject<CreativeModeTab> GEMS_TAB = CREATIVE_MODE_TABS.register("infinite_gems",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GEMS.get("ruby").get()))
                    .title(Component.translatable("creativetab.infinite_gems"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.GEMS.containsKey(material)) output.accept(ModItems.GEMS.get(material).get());
                        }
                    }).build());

    // 3. CLEAN INGOTS TAB (Only items now)
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

    public static final RegistryObject<CreativeModeTab> RAW_ORES_TAB = CREATIVE_MODE_TABS.register("infinite_raw_ores",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAW_ORES.get("tin").get()))
                    .title(Component.translatable("creativetab.infinite_raw_ores"))
                    .displayItems((parameters, output) -> {
                        for (String material : ModItems.MATERIALS) {
                            if (ModItems.RAW_ORES.containsKey(material)) output.accept(ModItems.RAW_ORES.get(material).get());
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

    private static boolean isGemstone(String material) {
        return material.equals("ruby") || material.equals("sapphire") || material.equals("topaz") || material.equals("amethyst") ||
                material.equals("opal") || material.equals("aquamarine") || material.equals("peridot") || material.equals("garnet") ||
                material.equals("jade") || material.equals("tourmaline") || material.equals("citrine") || material.equals("tanzanite") ||
                material.equals("amber") || material.equals("malachite") || material.equals("onyx") || material.equals("jasper") ||
                material.equals("agate") || material.equals("turquoise") || material.equals("tigerseye") || material.equals("moonstone") ||
                material.equals("sunstone") || material.equals("morganite") || material.equals("iolite") || material.equals("alexandrite") ||
                material.equals("carnelian");
    }
}