package me.kctops6.infiniteresources;

import java.util.HashMap;
import java.util.Map;

public class MaterialColors {
    public static final Map<String, Integer> COLORS = new HashMap<>();

    static {
        // Base Vanilla Metals & Items
        COLORS.put("iron", 0xD8D8D8);
        COLORS.put("gold", 0xFCE244);
        COLORS.put("copper", 0xf07500);
        COLORS.put("diamond", 0x5CC4C4);
        COLORS.put("emerald", 0x34C46C);
        COLORS.put("lapis", 0x2444A4);
        COLORS.put("obsidian", 0x1e042e);
        COLORS.put("coal", 0x4A4A4A);

        // Modded Metals & Alloys
        COLORS.put("steel", 0x707070); //
        COLORS.put("brass", 0xD6B85C); //
        COLORS.put("bronze", 0xAD743E); //
        COLORS.put("tin", 0x9CB4BC); //
        COLORS.put("lead", 0x485068); //
        COLORS.put("silver", 0xD2E6EC); //
        COLORS.put("nickel", 0xBCBC9C); //
        COLORS.put("aluminum", 0xD6E2E6); //
        COLORS.put("zinc", 0xA4B0BC); //
        COLORS.put("invar", 0xA0A8A0); //
        COLORS.put("electrum", 0xDEC464); //
        COLORS.put("constantan", 0xCC7C5C); //
        COLORS.put("osmium", 0xbdcdde);
        COLORS.put("uranium", 0x106402);
        COLORS.put("lumium", 0xdcc47d);
        COLORS.put("signalum", 0xae490e);
        COLORS.put("enderium", 0x0c5253);

        // Custom Gemstones (Fixes missing colors making gems render white)
        COLORS.put("ruby", 0xE62E4A);
        COLORS.put("sapphire", 0x2E5CE6);
        COLORS.put("topaz", 0xF29930);
        COLORS.put("amethyst", 0xA65246);
        COLORS.put("opal", 0xE0E6ED);
        COLORS.put("aquamarine", 0x7BE3E6);
        COLORS.put("peridot", 0x91DB3B);
        COLORS.put("garnet", 0x9C142B);
        COLORS.put("jade", 0x52B371);
        COLORS.put("tourmaline", 0xD64D8B);
        COLORS.put("citrine", 0xEBB841);
        COLORS.put("tanzanite", 0x5649CC);
        COLORS.put("amber", 0xEB962F);
        COLORS.put("malachite", 0x1B8A5A);
        COLORS.put("onyx", 0x242424);
        COLORS.put("jasper", 0xC74A36);
        COLORS.put("agate", 0xDE8568);
        COLORS.put("turquoise", 0x49E3C6);
        COLORS.put("tigerseye", 0x8F6224);
        COLORS.put("moonstone", 0xCBDDF0);
        COLORS.put("sunstone", 0xFA9B6B);
        COLORS.put("morganite", 0xF7A1B5);
        COLORS.put("iolite", 0x4D58B3);
        COLORS.put("alexandrite", 0x479486);
        COLORS.put("carnelian", 0xCC4E25);
    }
}