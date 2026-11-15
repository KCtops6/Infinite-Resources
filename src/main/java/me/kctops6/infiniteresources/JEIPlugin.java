package me.kctops6.infiniteresources;

import com.mojang.logging.LogUtils;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final ResourceLocation PLUGIN_ID = new ResourceLocation(InfiniteResources.MOD_ID, "jei_plugin");

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        List<ItemStack> itemsToHide = new ArrayList<>();
        String[] forms = {"gem", "ingot", "nugget", "dust", "plate", "raw"};

        for (String material : ModItems.MATERIALS) {
            for (String form : forms) {
                // Read granular per-item configuration options dynamically
                if (!ModConfig.isItemEnabled(material, form)) {
                    if (form.equals("gem") && ModItems.GEMS.containsKey(material)) {
                        itemsToHide.add(new ItemStack(ModItems.GEMS.get(material).get()));
                    }
                    else if (form.equals("ingot") && ModItems.INGOTS.containsKey(material)) {
                        itemsToHide.add(new ItemStack(ModItems.INGOTS.get(material).get()));
                    }
                    else if (form.equals("nugget") && ModItems.NUGGETS.containsKey(material)) {
                        itemsToHide.add(new ItemStack(ModItems.NUGGETS.get(material).get()));
                    }
                    else if (form.equals("dust") && ModItems.DUSTS.containsKey(material)) {
                        itemsToHide.add(new ItemStack(ModItems.DUSTS.get(material).get()));
                    }
                    else if (form.equals("plate") && ModItems.PLATES.containsKey(material)) {
                        itemsToHide.add(new ItemStack(ModItems.PLATES.get(material).get()));
                    }
                    else if (form.equals("raw") && ModItems.RAW_ORES.containsKey(material)) {
                        itemsToHide.add(new ItemStack(ModItems.RAW_ORES.get(material).get()));
                    }
                }
            }
        }

        if (!itemsToHide.isEmpty()) {
            registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, itemsToHide);
            LOGGER.info("JEI successfully hid {} config-disabled item variants.", itemsToHide.size());
        }
    }
}