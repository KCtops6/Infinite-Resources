package me.kctops6.infiniteresources;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashMap;
import java.util.Map;

public class ModConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final Map<String, ForgeConfigSpec.BooleanValue> ENABLED_MATERIALS = new HashMap<>();
    private static final Map<String, Boolean> RUNTIME_OVERRIDES = new HashMap<>();
    static {
        BUILDER.comment("Infinite Resources Unification Configuration").push("materials");
        for (String material : ModItems.MATERIALS) {
            ENABLED_MATERIALS.put(material, BUILDER
                    .comment("Enable/Disable all unified items for " + material + " (Ingot, Nugget, Dust, Plate)")
                    .define("enable_" + material, true));
        }
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
    public static void setRuntimeOverride(String material, boolean enabled) {
        RUNTIME_OVERRIDES.put(material, enabled);
    }
    public static boolean isMaterialEnabled(String material) {
        if (RUNTIME_OVERRIDES.containsKey(material)) return RUNTIME_OVERRIDES.get(material);
        if (ENABLED_MATERIALS.containsKey(material)) return ENABLED_MATERIALS.get(material).get();
        return false;
    }
}