package me.kctops6.infiniteresources;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> INGOTS = commonTag("ingots");
        public static final TagKey<Item> NUGGETS = commonTag("nuggets");
        public static final TagKey<Item> DUSTS = commonTag("dusts");
        public static final TagKey<Item> PLATES = commonTag("plates");

        private static TagKey<Item> commonTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }
}