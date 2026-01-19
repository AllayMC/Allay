package org.allaymc.server.block.component.pressureplate;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.world.sound.PressurePlateSound;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;
import org.joml.primitives.AABBd;

import java.time.Duration;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.REDSTONE_SIGNAL;

/**
 * Base implementation for pressure plates.
 * Binary pressure plates (stone, wooden) output 0 or 15.
 *
 * @author daoge_cmd
 */
public class BlockPressurePlateBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final Duration CHECK_INTERVAL = Duration.ofMillis(500);

    public BlockPressurePlateBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        // Pressure plate requires a block below with a full top surface
        BlockState below = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!below.getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
            return false;
        }
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        // Break if block below no longer has a full top surface
        if (face == BlockFace.DOWN && !neighbor.getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
            block.breakBlock();
        }
    }

    @Override
    public void onEntityInside(Block block, Entity entity) {
        int currentSignal = block.getPropertyValue(REDSTONE_SIGNAL);
        if (currentSignal == 0) {
            // Activate the plate
            int newSignal = calculateSignal(block);
            if (newSignal > 0) {
                updateSignal(block, newSignal);
            }
        }
    }

    @Override
    public void onScheduledUpdate(Block block) {
        int currentSignal = block.getPropertyValue(REDSTONE_SIGNAL);
        int newSignal = calculateSignal(block);

        if (newSignal != currentSignal) {
            updateSignal(block, newSignal);
        }

        // Schedule next check if still active
        if (newSignal > 0) {
            block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(block.getPosition(), CHECK_INTERVAL);
        }
    }

    /**
     * Calculates the signal strength based on entities on the plate.
     * For binary plates, returns 15 if any entity is present, 0 otherwise.
     *
     * @param block the pressure plate block
     * @return the signal strength (0 or 15)
     */
    protected int calculateSignal(Block block) {
        int entityCount = countEntitiesOnPlate(block);
        return entityCount > 0 ? MAX_REDSTONE_POWER : 0;
    }

    /**
     * Counts the number of entities standing on the pressure plate.
     *
     * @param block the pressure plate block
     * @return the number of entities on the plate
     */
    protected int countEntitiesOnPlate(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        // Use the block's shape for entity detection
        AABBd detectionAABB = block.getBlockStateData().computeOffsetShape(pos).unionAABB();

        int count = 0;
        for (Entity entity : dimension.getEntities().values()) {
            if (entity.getOffsetAABB().intersectsAABB(detectionAABB)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Updates the signal level and triggers neighbor updates.
     *
     * @param block     the pressure plate block
     * @param newSignal the new signal level
     */
    protected void updateSignal(Block block, int newSignal) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        int oldSignal = block.getPropertyValue(REDSTONE_SIGNAL);

        dimension.updateBlockProperty(REDSTONE_SIGNAL, newSignal, pos);

        // Play sound
        if (newSignal > 0 && oldSignal == 0) {
            block.addSound(new PressurePlateSound(block.getBlockState(), true));
            // Schedule periodic check
            dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, CHECK_INTERVAL);
        } else if (newSignal == 0 && oldSignal > 0) {
            block.addSound(new PressurePlateSound(block.getBlockState(), false));
        }

        // Update neighbors
        dimension.updateAround(pos);
        // Also update the block below for strong power
        dimension.updateAround(BlockFace.DOWN.offsetPos(pos));
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        // Check for entities immediately after placement
        // Use newBlockState to get the correct shape for entity detection
        Block newBlock = new Block(newBlockState, oldBlock.getPosition());
        int signal = calculateSignal(newBlock);
        if (signal > 0) {
            updateSignal(newBlock, signal);
        }
    }

    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);
        // Update block below when removed (if was active, for strong power)
        if (oldBlock.getPropertyValue(REDSTONE_SIGNAL) > 0) {
            oldBlock.getDimension().updateAround(BlockFace.DOWN.offsetPos(oldBlock.getPosition()));
        }
    }

    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return block.getPropertyValue(REDSTONE_SIGNAL);
    }

    @Override
    public int getStrongPower(Block block, BlockFace face) {
        // Provide strong power downward
        if (face == BlockFace.DOWN) {
            return block.getPropertyValue(REDSTONE_SIGNAL);
        }
        return 0;
    }

    @Override
    public boolean isPowerSource() {
        return true;
    }
}
