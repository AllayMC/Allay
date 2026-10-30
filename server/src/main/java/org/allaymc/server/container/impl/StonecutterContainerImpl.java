package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.StonecutterContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;

/**
 * {@code StonecutterContainerImpl} supplies stonecutter input and result storage plus
 * the single-ingredient crafting representation used for recipe matching. The input
 * sample contains one item with the source network identity, allowing matching without
 * reducing the count retained in the container.
 *
 * @author IWareQ
 */
public class StonecutterContainerImpl extends BlockContainerImpl implements StonecutterContainer {
    /**
     * Creates empty two-slot storage using the stonecutter container type identity.
     */
    public StonecutterContainerImpl() {
        super(ContainerTypes.STONECUTTER);
    }

    /**
     * Builds a single-ingredient crafting input from the stonecutter's current input
     * sample. Empty storage contributes the shared air sentinel; occupied input
     * contributes a one-item copy with the source network ID.
     */
    @Override
    public RecipeInput createRecipeInput() {
        return new CraftingRecipeInput(pickOneInput());
    }

    /**
     * Returns a one-item copy of the stored stonecutting input with its network ID
     * preserved. An empty input returns the shared air sentinel, and the stored
     * ingredient count remains unchanged.
     */
    protected ItemStack pickOneInput() {
        if (isEmpty(INPUT_SLOT)) {
            return ItemAirStack.AIR_STACK;
        }

        var copy = getItemStack(INPUT_SLOT).copy(false);
        copy.setCount(1);
        return copy;
    }
}
