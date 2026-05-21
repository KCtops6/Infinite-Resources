package me.kctops6.infiniteresources;

public enum NuggetStyle {
    IRON("iron"),
    GOLD("gold"),
    COPPER("copper");

    private final String name;

    NuggetStyle(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}