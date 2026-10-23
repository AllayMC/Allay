package org.allaymc.server.item.component;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * {@code ItemComparatorBaseComponentImpl} maps a comparator item's placement to
 * {@code BlockTypes.UNPOWERED_COMPARATOR}. The shared placement helper performs the block
 * behavior call and successful-placement effects, while this component chooses
 * the block variant independently of the item's default block mapping.
 *
 * @author daoge_cmd
 */
public class ItemComparatorBaseComponentImpl extends ItemBaseComponentImpl {

    /**
     * Creates the comparator item component from the supplied stack initialization.
     */
    public ItemComparatorBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Attempts to place the default {@code BlockTypes.UNPOWERED_COMPARATOR} state through
     * the shared placement helper. {@code placementInfo} may be {@code null};
     * successful placement uses the helper's sound, block-entity, consumption, and
     * component-event handling.
     */
    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return tryPlaceBlockState(dimension, BlockTypes.UNPOWERED_COMPARATOR.getDefaultState(), placeBlockPos, placementInfo);
    }
}
