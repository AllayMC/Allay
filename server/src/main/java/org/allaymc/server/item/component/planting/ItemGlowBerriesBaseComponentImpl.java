package org.allaymc.server.item.component.planting;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * {@code ItemGlowBerriesBaseComponentImpl} places the default cave vines block
 * state when its item is planted. The inherited placement path owns block
 * eligibility, collision checks, and placement side effects, while this
 * specialization selects {@code BlockTypes.CAVE_VINES} instead of the item's
 * mapped block type.
 *
 * @author daoge_cmd
 */
public class ItemGlowBerriesBaseComponentImpl extends ItemBaseComponentImpl {
    /**
     * Creates crop-placement behavior from the supplied item initialization
     * data.
     */
    public ItemGlowBerriesBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Attempts to place the default {@code BlockTypes.CAVE_VINES} state at
     * {@code placeBlockPos}. The inherited helper applies placement or
     * combination rules and returns their result; player context enables
     * collision checks and successful-placement consumption.
     */
    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return tryPlaceBlockState(dimension, BlockTypes.CAVE_VINES.getDefaultState(), placeBlockPos, placementInfo);
    }
}
