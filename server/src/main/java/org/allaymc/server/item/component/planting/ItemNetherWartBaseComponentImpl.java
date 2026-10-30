package org.allaymc.server.item.component.planting;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * {@code ItemNetherWartBaseComponentImpl} places the default nether wart block
 * state when its item is planted. The inherited placement path owns block
 * eligibility, collision checks, and placement side effects, while this
 * specialization selects {@code BlockTypes.NETHER_WART} instead of the item's
 * mapped block type.
 *
 * @author daoge_cmd
 */
public class ItemNetherWartBaseComponentImpl extends ItemBaseComponentImpl {
    /**
     * Creates crop-placement behavior from the supplied item initialization
     * data.
     */
    public ItemNetherWartBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Attempts to place the default {@code BlockTypes.NETHER_WART} state at
     * {@code placeBlockPos}. The inherited helper applies placement or
     * combination rules and returns their result; player context enables
     * collision checks and successful-placement consumption.
     */
    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return tryPlaceBlockState(dimension, BlockTypes.NETHER_WART.getDefaultState(), placeBlockPos, placementInfo);
    }
}
