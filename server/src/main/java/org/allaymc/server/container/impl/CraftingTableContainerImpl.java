package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.CraftingTableContainer;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;

/**
 * {@code CraftingTableContainerImpl} supplies the nine-slot crafting table input grid
 * and its recipe representation. Recipe input follows row-major slot order and uses
 * one-item samples, so matching observes the grid's item identities without consuming
 * the stored ingredient quantities.
 *
 * @author daoge_cmd
 */
public class CraftingTableContainerImpl extends CraftingContainerImpl implements CraftingTableContainer {
    /**
     * Creates empty nine-slot storage using the crafting table container identity.
     */
    public CraftingTableContainerImpl() {
        super(ContainerTypes.CRAFTING_TABLE);
    }

    /**
     * Builds a three-by-three crafting input from one-item samples in row-major order.
     * Sample copies retain their source network IDs, and creating the input does not
     * reduce the stored ingredient counts.
     */
    @Override
    public RecipeInput createRecipeInput() {
        return new CraftingRecipeInput(
                pickOne(0), pickOne(1), pickOne(2),
                pickOne(3), pickOne(4), pickOne(5),
                pickOne(6), pickOne(7), pickOne(8)
        );
    }
}
