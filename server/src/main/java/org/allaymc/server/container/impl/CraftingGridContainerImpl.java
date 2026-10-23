package org.allaymc.server.container.impl;

import lombok.Getter;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.math.position.Position3ic;

/**
 * {@code CraftingGridContainerImpl} represents the player's four-slot personal crafting
 * grid and supplies its two-by-two recipe input. It reuses crafting sample construction
 * but has no world block location, so both inherited block-position operations
 * explicitly reject use.
 *
 * @author daoge_cmd
 */
@Getter
public class CraftingGridContainerImpl extends CraftingContainerImpl {
    /**
     * Creates empty four-slot storage using the personal crafting-grid container
     * identity.
     */
    public CraftingGridContainerImpl() {
        super(ContainerTypes.CRAFTING_GRID);
    }

    /**
     * Builds two-by-two recipe input in row-major order using one-item samples. Occupied
     * samples retain the source network ID, while empty samples use the shared air
     * sentinel without consuming ingredients.
     */
    @Override
    public RecipeInput createRecipeInput() {
        return new CraftingRecipeInput(
                pickOne(0), pickOne(1),
                pickOne(2), pickOne(3)
        );
    }

    /**
     * Rejects block-position lookup because the personal crafting grid has no world
     * block. Every call throws {@code UnsupportedOperationException}; callers must treat
     * this container as player storage when opening interfaces.
     */
    @Override
    public Position3ic getBlockPos() {
        throw new UnsupportedOperationException();
    }

    /**
     * Rejects attempts to assign a world block to the personal crafting grid. Every call
     * throws {@code UnsupportedOperationException}, regardless of {@code blockPos},
     * because this container has no block location.
     */
    @Override
    public void setBlockPos(Position3ic blockPos) {
        throw new UnsupportedOperationException();
    }
}
