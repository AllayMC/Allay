package org.allaymc.server.block.component.rail;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.time.Duration;

/**
 * {@code BlockDetectorRailBaseComponentImpl} provides straight-rail placement and
 * redstone output for the detector rail's stored active bit. {@code onMinecartEnter}
 * can activate a matching rail and notify neighboring circuits. Scheduled detection and
 * inventory-based comparator output remain unimplemented, so this component does not
 * automatically clear activation or produce inventory signal strength.
 *
 * @author daoge_cmd
 */
public class BlockDetectorRailBaseComponentImpl extends BlockRailBaseComponentImpl {

    /**
     * {@code DETECTION_INTERVAL} reserves a one-second detector recheck interval; the
     * current empty scheduled callback never uses it to schedule detection.
     */
    private static final Duration DETECTION_INTERVAL = Duration.ofMillis(1000);

    /**
     * Creates a detector rail component using the six-direction straight-rail property.
     */
    public BlockDetectorRailBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType, false, BlockPropertyTypes.RAIL_DIRECTION_6);
    }

    /**
     * Returns maximum weak power when the detector rail snapshot is active. The same
     * result applies to every queried face, and an inactive {@code RAIL_DATA_BIT}
     * produces {@code 0}.
     */
    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return block.getPropertyValue(BlockPropertyTypes.RAIL_DATA_BIT) ? MAX_REDSTONE_POWER : 0;
    }

    /**
     * Returns maximum strong power downward from an active detector rail. Inactive
     * rails and other faces return {@code 0}, allowing only the support below to
     * receive strong power.
     */
    @Override
    public int getStrongPower(Block block, BlockFace face) {
        if (!block.getPropertyValue(BlockPropertyTypes.RAIL_DATA_BIT)) {
            return 0;
        }
        return face == BlockFace.DOWN ? MAX_REDSTONE_POWER : 0;
    }

    /**
     * Returns whether detector rails advertise a comparator input override.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns {@code 0} for the current detector comparator implementation. The active
     * bit does not change that result because minecart inventory signal calculation has
     * not been implemented here.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        if (!block.getPropertyValue(BlockPropertyTypes.RAIL_DATA_BIT)) {
            return 0;
        }
        // TODO: Derive comparator strength from a detected minecart's inventory
        // when detector minecart lookup is implemented; activation alone has no inventory signal.
        return 0;
    }

    /**
     * Performs no detector recheck in the current implementation. This callback neither
     * scans entities nor clears {@code RAIL_DATA_BIT}, so invoking it cannot release a
     * previously activated detector rail.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        // TODO: Scan minecarts in the rail bounds, update RAIL_DATA_BIT, and
        // reschedule active detection so activated rails can release after carts leave.
    }

    /**
     * Activates an inactive detector rail at the supplied position and notifies its
     * neighbors and support. A different block type is ignored, and this method does
     * not schedule detection or later deactivation.
     */
    public void onMinecartEnter(Dimension dimension, Vector3ic pos) {
        var blockState = dimension.getBlockState(pos);
        if (blockState.getBlockType() != blockType) return;

        if (!blockState.getPropertyValue(BlockPropertyTypes.RAIL_DATA_BIT)) {
            dimension.updateBlockProperty(BlockPropertyTypes.RAIL_DATA_BIT, true, pos);
            dimension.updateAround(pos);
            dimension.updateAround(BlockFace.DOWN.offsetPos(pos));
        }
    }
}
