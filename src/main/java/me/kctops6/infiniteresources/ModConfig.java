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
    private static boolean configLoaded = false;

    static {
        BUILDER.comment("Infinite Resources Granular Configuration").push("materials");

        String[] forms = {"ingot", "nugget", "dust", "plate", "gear"};

        for (String material : ModItems.MATERIALS) {
            BUILDER.push(material);
            for (String form : forms) {
                if (isVanillaOmitted(material, form)) continue;

                if (form.equals("nugget")) {
                    // Create an Enum selection configuration specifically for nuggets
                    NUGGET_STYLES.put(material, BUILDER
                            .comment("Texture style selection for " + material + "_nugget. Options: IRON, GOLD, COPPER")
                            .defineEnum("nugget_texture_style", NuggetStyle.IRON));
                } else {
                    // Everything else uses our classic on/off switches
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
            return form.equals("ingot") || form.equals("nugget");
        }
        if (material.equals("diamond") || material.equals("emerald") || material.equals("lapis")) {
            return form.equals("ingot");
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

        // Nugget visibility is inferred: if it has a style config, it's enabled!
        if (form.equals("nugget")) {
            return NUGGET_STYLES.containsKey(material);
        }

        String compositeKey = material + "_" + form;
        if (TOGGLES.containsKey(compositeKey)) {
            return TOGGLES.get(compositeKey).get();
        }
        return true;
    }

    public static NuggetStyle getNuggetStyle(String material) {
        if (!configLoaded || !NUGGET_STYLES.containsKey(material)) {
            return NuggetStyle.IRON; // Default fallback
        }
        return NUGGET_STYLES.get(material).get();
    }
}