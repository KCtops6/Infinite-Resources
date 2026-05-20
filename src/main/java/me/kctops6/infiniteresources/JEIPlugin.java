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

        for (String material : ModItems.MATERIALS) {
            // Check the dynamic configuration system
            if (!ModConfig.isMaterialEnabled(material)) {
                // If the material has no external mod equivalents, queue all 4 variants to be hidden
                if (ModItems.INGOTS.containsKey(material)) {
                    itemsToHide.add(new ItemStack(ModItems.INGOTS.get(material).get()));
                }
                if (ModItems.NUGGETS.containsKey(material)) {
                    itemsToHide.add(new ItemStack(ModItems.NUGGETS.get(material).get()));
                }
                if (ModItems.DUSTS.containsKey(material)) {
                    itemsToHide.add(new ItemStack(ModItems.DUSTS.get(material).get()));
                }
                if (ModItems.PLATES.containsKey(material)) {
                    itemsToHide.add(new ItemStack(ModItems.PLATES.get(material).get()));
                }
            }
        }

        // Forcefully hide the compiled list of orphaned items from the JEI layout at runtime
        if (!itemsToHide.isEmpty()) {
            registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, itemsToHide);
            System.out.println("[" + InfiniteResources.MOD_ID + "] JEI successfully hid " + itemsToHide.size() + " orphaned item forms.");
        }
    }
}