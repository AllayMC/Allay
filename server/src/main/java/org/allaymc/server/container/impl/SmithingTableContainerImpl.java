package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.SmithingTableContainer;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.item.recipe.input.SmithingRecipeInput;

/**
 * {@code SmithingTableContainerImpl} supplies smithing storage and constructs the
 * template, input, and material tuple used by recipe matching. The recipe input retains
 * the stored stack references, so matching and later action processing observe the same
 * mutable ingredient objects.
 *
 * @author IWareQ
 */
public class SmithingTableContainerImpl extends BlockContainerImpl implements SmithingTableContainer {

    /**
     * Creates empty four-slot storage using the smithing table container identity.
     */
    public SmithingTableContainerImpl() {
        super(ContainerTypes.SMITHING_TABLE);
    }

    /**
     * Builds smithing recipe input in template, input-item, and material order. The
     * tuple retains the container's current stack references rather than copying them,
     * so consumers must treat those values as shared.
     */
    @Override
    public RecipeInput createRecipeInput() {
        return new SmithingRecipeInput(getTemplate(), getInput(), getMaterial());
    }
}
