package me.kctops6.infiniteresources;

import java.util.HashMap;
import java.util.Map;

public class MaterialColors {
    public static final Map<String, Integer> COLORS = new HashMap<>();

    static {
        // Base Vanilla Metals & Items
        COLORS.put("iron", 0xFFD8D8D8);
        COLORS.put("gold", 0xFFFCE244);
        COLORS.put("copper", 0xFFF07500);
        COLORS.put("diamond", 0xFF5CC4C4);
        COLORS.put("emerald", 0xFF34C46C);
        COLORS.put("lapis", 0xFF2444A4);
        COLORS.put("obsidian", 0xFF1E042E);
        COLORS.put("coal", 0xFF4A4A4A);
        COLORS.put("netherite", 0xFF433D40);

        // Modded Metals & Alloys
        COLORS.put("steel", 0xFF707070);
        COLORS.put("brass", 0xFFD6B85C);
        COLORS.put("bronze", 0xFFAD743E);
        COLORS.put("tin", 0xFF9CB4BC);
        COLORS.put("lead", 0xFF485068);
        COLORS.put("silver", 0xFFD2E6EC);
        COLORS.put("nickel", 0xFFBCBC9C);
        COLORS.put("aluminum", 0xFFD6E2E6);
        COLORS.put("zinc", 0xFFA4B0BC);
        COLORS.put("invar", 0xFFA0A8A0);
        COLORS.put("electrum", 0xFFDEC464);
        COLORS.put("constantan", 0xFFCC7C5C);
        COLORS.put("osmium", 0xFFBDCDDE);
        COLORS.put("uranium", 0xFF106402);
        COLORS.put("lumium", 0xFFDCC47D);
        COLORS.put("signalum", 0xFFAE490E);
        COLORS.put("enderium", 0xFF0C5253);

        // Custom Gemstones
        COLORS.put("ruby", 0xFFE62E4A);
        COLORS.put("sapphire", 0xFF2E5CE6);
        COLORS.put("topaz", 0xFFF29930);
        COLORS.put("amethyst", 0xFFA65246);
        COLORS.put("opal", 0xFFE0E6ED);
        COLORS.put("aquamarine", 0xFF7BE3E6);
        COLORS.put("peridot", 0xFF91DB3B);
        COLORS.put("garnet", 0xFF9C142B);
        COLORS.put("jade", 0xFF52B371);
        COLORS.put("tourmaline", 0xFFD64D8B);
        COLORS.put("citrine", 0xFFEBB841);
        COLORS.put("tanzanite", 0xFF5649CC);
        COLORS.put("amber", 0xFFEB962F);
        COLORS.put("malachite", 0xFF1B8A5A);
        COLORS.put("onyx", 0xFF242424);
        COLORS.put("jasper", 0xFFC74A36);
        COLORS.put("agate", 0xFFDE8568);
        COLORS.put("turquoise", 0xFF49E3C6);
        COLORS.put("tigerseye", 0xFF8F6224);
        COLORS.put("moonstone", 0xFFCBDDF0);
        COLORS.put("sunstone", 0xFFFA9B6B);
        COLORS.put("morganite", 0xFFF7A1B5);
        COLORS.put("iolite", 0xFF4D58B3);
        COLORS.put("alexandrite", 0xFF479486);
        COLORS.put("carnelian", 0xFFCC4E25);
    }

    /**
     * Helper method to safety-check and return material colors.
     * Automatically applies full opacity if an 24-bit color is requested.
     */
    public static int getColor(String materialName) {
        int color = COLORS.getOrDefault(materialName, 0xFFFFFFFF);
        if ((color & 0xFF000000) == 0) {
            color |= 0xFF000000;
        }
        return color;
    }
}