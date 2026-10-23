package org.allaymc.server.item.component.planting;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * {@code ItemPotatoBaseComponentImpl} places the default potatoes block state
 * when its item is planted. The inherited placement path owns block
 * eligibility, collision checks, and placement side effects, while this
 * specialization selects {@code BlockTypes.POTATOES} instead of the item's
 * mapped block type.
 *
 * @author daoge_cmd
 */
public class ItemPotatoBaseComponentImpl extends ItemBaseComponentImpl {
    /**
     * Creates crop-placement behavior from the supplied item initialization
     * data.
     */
    public ItemPotatoBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Attempts to place the default {@code BlockTypes.POTATOES} state at
     * {@code placeBlockPos}. The inherited helper applies placement or
     * combination rules and returns their result; player context enables
     * collision checks and successful-placement consumption.
     */
    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return tryPlaceBlockState(dimension, BlockTypes.POTATOES.getDefaultState(), placeBlockPos, placementInfo);
    }
}
