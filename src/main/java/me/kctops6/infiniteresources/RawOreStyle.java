package me.kctops6.infiniteresources;

public enum RawOreStyle {
    IRON("iron"),
    GOLD("gold"),
    COPPER("copper");

    private final String name;

    RawOreStyle(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}