package me.kctops6.infiniteresources;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import java.util.HashMap;
import java.util.Map;

public class ModConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    private static final Map<String, ForgeConfigSpec.BooleanValue> TOGGLES = new HashMap<>();
    private static boolean configLoaded = false;

    static {
        BUILDER.comment("Infinite Resources Granular Unification Configuration").push("materials");

        // Forms tracked by the mod (including gears)
        String[] forms = {"ingot", "nugget", "dust", "plate", "gear"};

        for (String material : ModItems.MATERIALS) {
            BUILDER.push(material);
            for (String form : forms) {
                if (isVanillaOmitted(material, form)) continue;

                String compositeKey = material + "_" + form;
                TOGGLES.put(compositeKey, BUILDER
                        .comment("Enable/Disable JEI visibility and pickup functionality for: " + material + " " + form)
                        .define("enable_" + form, true));
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

    // Listens for Forge's configuration file reading phase to activate the toggles safely
    public static void onConfigLoad(final ModConfigEvent event) {
        if (event.getConfig().getSpec() == SPEC) {
            configLoaded = true;
        }
    }

    public static boolean isItemEnabled(String material, String form) {
        // SAFETY BYPASS: If Forge hasn't finished reading the TOML file yet,
        // assume everything is ENABLED so JEI doesn't prematurely hide items!
        if (!configLoaded) {
            return true;
        }

        String compositeKey = material + "_" + form;
        if (TOGGLES.containsKey(compositeKey)) {
            return TOGGLES.get(compositeKey).get();
        }
        return true;
    }
}