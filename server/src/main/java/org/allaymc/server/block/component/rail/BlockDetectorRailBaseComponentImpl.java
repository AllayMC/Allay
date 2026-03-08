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
 * Component for detector rails. Outputs redstone signal when active
 * (RAIL_DATA_BIT = true). Detection of minecart entities is stubbed
 * pending entity system support.
 *
 * @author daoge_cmd
 */
public class BlockDetectorRailBaseComponentImpl extends BlockRailBaseComponentImpl {

    private static final Duration DETECTION_INTERVAL = Duration.ofMillis(1000); // 20 ticks

    public BlockDetectorRailBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType, false, BlockPropertyTypes.RAIL_DIRECTION_6);
    }

    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return block.getPropertyValue(BlockPropertyTypes.RAIL_DATA_BIT) ? MAX_REDSTONE_POWER : 0;
    }

    @Override
    public int getStrongPower(Block block, BlockFace face) {
        if (!block.getPropertyValue(BlockPropertyTypes.RAIL_DATA_BIT)) {
            return 0;
        }
        // Detector rail provides strong power only downward (to block below)
        return face == BlockFace.DOWN ? MAX_REDSTONE_POWER : 0;
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        if (!block.getPropertyValue(BlockPropertyTypes.RAIL_DATA_BIT)) {
            return 0;
        }
        // TODO: When minecart entities are implemented, check if the minecart
        // is an inventory holder and return proportional signal strength.
        // For now, return 0 as there are no minecart entities yet.
        return 0;
    }

    @Override
    public void onScheduledUpdate(Block block) {
        // TODO: When minecart entities are implemented, scan for minecarts
        // within this rail's bounding box. If found, set RAIL_DATA_BIT = true
        // and schedule re-check. If not found, set RAIL_DATA_BIT = false.
        //
        // Example future implementation:
        // var dimension = block.getDimension();
        // var pos = block.getPosition();
        // boolean hasMinecart = scanForMinecart(dimension, pos);
        // boolean currentlyActive = block.getPropertyValue(BlockPropertyTypes.RAIL_DATA_BIT);
        // if (hasMinecart != currentlyActive) {
        //     dimension.updateBlockProperty(BlockPropertyTypes.RAIL_DATA_BIT, hasMinecart, pos);
        //     dimension.updateAround(pos);
        //     dimension.updateAround(BlockFace.DOWN.offsetPos(pos));
        // }
        // if (hasMinecart) {
        //     block.scheduleUpdateInDelay(DETECTION_INTERVAL);
        // }
    }

    /**
     * Called when a minecart entity enters this rail's position.
     * Should activate the detector rail and start the scheduled update cycle.
     * This method will be called by the entity system when minecarts are implemented.
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
