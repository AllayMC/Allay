package org.allaymc.server.item.component;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * {@code ItemBedBaseComponentImpl} places the block state associated with a bed
 * item. Its placement call disables the shared helper's entity-collision check
 * while retaining the bed block behavior's placement rules and the helper's
 * successful-placement effects.
 *
 * @author daoge_cmd
 */
public class ItemBedBaseComponentImpl extends ItemBaseComponentImpl {
    /**
     * Creates a bed base component using the supplied stack initialization.
     */
    public ItemBedBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Attempts bed placement without the base helper's entity-collision check.
     * Callers must supply player interaction information because the bed block
     * behavior reads the player's facing from {@code placementInfo}. Successful
     * placement retains the helper's usual post-placement effects.
     */
    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return tryPlaceBlockState(dimension, toBlockState(), placeBlockPos, placementInfo, false);
    }
}
