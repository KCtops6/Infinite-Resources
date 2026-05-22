package me.kctops6.infiniteresources;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import java.util.HashMap;
import java.util.Map;

public class ModConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    private static final Map<String, ForgeConfigSpec.BooleanValue> TOGGLES = new HashMap<>();
    private static final Map<String, ForgeConfigSpec.EnumValue<NuggetStyle>> NUGGET_STYLES = new HashMap<>();
    private static final Map<String, ForgeConfigSpec.EnumValue<RawOreStyle>> RAW_ORE_STYLES = new HashMap<>();
    private static boolean configLoaded = false;

    static {
        BUILDER.comment("Infinite Resources Granular Unification Configuration").push("materials");

        String[] forms = {"ingot", "nugget", "dust", "plate", "gear", "raw"};

        for (String material : ModItems.MATERIALS) {
            BUILDER.push(material);
            for (String form : forms) {
                if (isVanillaOmitted(material, form)) continue;

                // FIX: If it's a raw ore form, but this material doesn't have a registered raw ore, skip it entirely!
                if (form.equals("raw") && !ModItems.RAW_ORES.containsKey(material)) continue;

                if (form.equals("nugget")) {
                    NUGGET_STYLES.put(material, BUILDER
                            .comment("Texture style selection for " + material + "_nugget. Options: IRON, GOLD, COPPER")
                            .defineEnum("nugget_texture_style", NuggetStyle.IRON));
                } else if (form.equals("raw")) {
                    String compositeKey = material + "_" + form;
                    // 1. Boolean Switch to enable/disable the raw ore item completely
                    TOGGLES.put(compositeKey, BUILDER
                            .comment("Enable/Disable JEI visibility and functionality for raw_" + material)
                            .define("enable_raw", true));

                    // 2. Enum Style Configuration for the texture template
                    RAW_ORE_STYLES.put(material, BUILDER
                            .comment("Texture style selection for raw_" + material + ". Options: IRON, GOLD, COPPER")
                            .defineEnum("raw_ore_texture_style", RawOreStyle.IRON));
                } else {
                    String compositeKey = material + "_" + form;
                    TOGGLES.put(compositeKey, BUILDER
                            .comment("Enable/Disable JEI visibility and functionality for: " + material + " " + form)
                            .define("enable_" + form, true));
                }
            }
            BUILDER.pop();
        }

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    private static boolean isVanillaOmitted(String material, String form) {
        if (material.equals("iron") || material.equals("gold")) {
            return form.equals("ingot") || form.equals("nugget") || form.equals("raw");
        }
        if (material.equals("diamond") || material.equals("emerald") || material.equals("lapis")) {
            return form.equals("ingot") || form.equals("raw");
        }
        return false;
    }

    public static void onConfigLoad(final ModConfigEvent event) {
        if (event.getConfig().getSpec() == SPEC) {
            configLoaded = true;
        }
    }

    public static boolean isItemEnabled(String material, String form) {
        if (!configLoaded) return true;

        if (form.equals("nugget")) return NUGGET_STYLES.containsKey(material);

        String compositeKey = material + "_" + form;
        if (TOGGLES.containsKey(compositeKey)) {
            return TOGGLES.get(compositeKey).get();
        }
        return true;
    }

    public static NuggetStyle getNuggetStyle(String material) {
        if (!configLoaded || !NUGGET_STYLES.containsKey(material)) return NuggetStyle.IRON;
        return NUGGET_STYLES.get(material).get();
    }

    public static RawOreStyle getRawOreStyle(String material) {
        if (!configLoaded || !RAW_ORE_STYLES.containsKey(material)) return RawOreStyle.IRON;
        return RAW_ORE_STYLES.get(material).get();
    }
}