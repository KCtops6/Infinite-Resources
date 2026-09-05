package me.kctops6.infiniteresources;

import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.Map;

public class ModConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    private static final Map<String, ModConfigSpec.BooleanValue> TOGGLES = new HashMap<>();
    private static final Map<String, ModConfigSpec.EnumValue<NuggetStyle>> NUGGET_STYLES = new HashMap<>();
    private static final Map<String, ModConfigSpec.EnumValue<RawOreStyle>> RAW_ORE_STYLES = new HashMap<>();
    private static final Map<String, ModConfigSpec.EnumValue<GemStyle>> GEM_STYLES = new HashMap<>();
    private static boolean configLoaded = false;

    static {
        BUILDER.comment("Infinite Resources Configuration Profile").push("materials");

        // Loop including "gem" as a dynamic component item check
        String[] forms = {"gem", "ingot", "nugget", "dust", "plate", "raw"};

        for (String material : ModItems.MATERIALS) {
            BUILDER.push(material);
            for (String form : forms) {
                if (isVanillaOmitted(material, form)) continue;

                // Strip validation logic rules
                if (form.equals("raw") && !ModItems.RAW_ORES.containsKey(material)) continue;
                if (form.equals("gem") && !ModItems.GEMS.containsKey(material)) continue;
                if (form.equals("ingot") && !ModItems.INGOTS.containsKey(material)) continue;

                if (form.equals("nugget")) {
                    NUGGET_STYLES.put(material, BUILDER
                            .comment("Texture style selection for " + material + "_nugget. Options: IRON, GOLD, COPPER")
                            .defineEnum("nugget_texture_style", NuggetStyle.IRON));
                } else if (form.equals("raw")) {
                    String compositeKey = material + "_" + form;
                    TOGGLES.put(compositeKey, BUILDER
                            .comment("Enable/Disable raw_" + material)
                            .define("enable_raw", true));
                    RAW_ORE_STYLES.put(material, BUILDER
                            .comment("Texture style selection for raw_" + material + ". Options: IRON, GOLD, COPPER")
                            .defineEnum("raw_ore_texture_style", RawOreStyle.IRON));
                } else if (form.equals("gem")) {
                    String compositeKey = material + "_" + form;
                    TOGGLES.put(compositeKey, BUILDER
                            .comment("Enable/Disable " + material + "_gem")
                            .define("enable_gem", true));
                    GEM_STYLES.put(material, BUILDER
                            .comment("Texture silhouette layout for " + material + "_gem. Options: DIAMOND, EMERALD, QUARTZ, AMETHYST, LAPIS")
                            .defineEnum("gem_texture_style", getReasonableGemDefault(material)));
                } else {
                    String compositeKey = material + "_" + form;
                    TOGGLES.put(compositeKey, BUILDER
                            .comment("Enable/Disable " + material + " " + form)
                            .define("enable_" + form, true));
                }
            }
            BUILDER.pop();
        }

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    private static GemStyle getReasonableGemDefault(String material) {
        switch (material) {
            case "ruby": case "sapphire": case "peridot": case "jade": case "alexandrite":
                return GemStyle.EMERALD;
            case "opal": case "aquamarine": case "citrine": case "agate": case "sunstone":
                return GemStyle.QUARTZ;
            case "amethyst": case "tanzanite": case "moonstone": case "iolite":
                return GemStyle.AMETHYST;
            case "amber": case "malachite": case "jasper": case "turquoise": case "tigerseye":
                return GemStyle.LAPIS;
            default:
                return GemStyle.DIAMOND; // topaz, garnet, tourmaline, onyx, morganite, carnelian
        }
    }

    private static boolean isVanillaOmitted(String material, String form) {
        if (material.equals("iron") || material.equals("gold")) {
            return form.equals("ingot") || form.equals("nugget") || form.equals("raw") || form.equals("gem");
        }
        if (material.equals("diamond") || material.equals("emerald") || material.equals("lapis")) {
            return form.equals("ingot") || form.equals("raw") || form.equals("gem");
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
        if (form.equals("nugget") && NUGGET_STYLES.containsKey(material)) return true;

        String compositeKey = material + "_" + form;
        if (TOGGLES.containsKey(compositeKey)) {
            return TOGGLES.get(compositeKey).get();
        }
        return true;
    }

    public static GemStyle getGemStyle(String material) {
        if (!configLoaded || !GEM_STYLES.containsKey(material)) return GemStyle.DIAMOND;
        return GEM_STYLES.get(material).get();
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