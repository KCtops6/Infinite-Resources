package me.kctops6.infiniteresources;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    private static final ResourceLocation PLUGIN_ID = new ResourceLocation(InfiniteResources.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<ItemStack> itemsToHide = new ArrayList<>();
        String[] forms = {"ingot", "nugget", "dust", "plate", "gear"};

        for (String material : ModItems.MATERIALS) {
            for (String form : forms) {
                // If a user turned off this specific item in the config, hide it!
                if (!ModConfig.isItemEnabled(material, form)) {

                    if (form.equals("ingot") && ModItems.INGOTS.containsKey(material)) {
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
                    else if (form.equals("gear") && false) {
                        // itemsToHide.add(new ItemStack(ModItems.GEARS.get(material).get()));
                    }
                }
            }
        }

        if (!itemsToHide.isEmpty()) {
            registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, itemsToHide);
            System.out.println("[" + InfiniteResources.MOD_ID + "] JEI successfully hid " + itemsToHide.size() + " config-disabled entries.");
        }
    }
}