package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftFacingDirection;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.time.Duration;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MINECRAFT_FACING_DIRECTION;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.POWERED_BIT;

/**
 * Implementation of the observer block.
 * Detects block changes in front of it and emits a 2-tick redstone pulse.
 * <p>
 * The observer has a 1-tick delay before activating (matching vanilla behavior),
 * then stays powered for 2 ticks before deactivating.
 *
 * @author daoge_cmd
 */
public class BlockObserverBaseComponentImpl extends BlockBaseComponentImpl {

    // In MCBE, observer activation is delayed by 2 redstone ticks (4 game ticks) due to MCPE-15793.
    // This bug causes redstone delays to be incorrect when components are activated by world changes.
    protected static final Duration ACTIVATION_DELAY = Duration.ofMillis(200); // 4 game ticks = 2 redstone ticks
    protected static final Duration PULSE_DURATION = Duration.ofMillis(100); // 2 game ticks = 1 redstone tick

    public BlockObserverBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            // Observer faces the direction the player is looking at (opposite of player facing)
            MinecraftFacingDirection facing = getPlacementFacing(placementInfo);
            blockState = blockState.setPropertyValue(MINECRAFT_FACING_DIRECTION, facing);
        }
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        // Only react to changes from the observed face (front)
        BlockFace observedFace = getObservedFace(block);
        if (face != observedFace) {
            return;
        }

        // Don't trigger if already powered (in pulse)
        if (block.getPropertyValue(POWERED_BIT)) {
            return;
        }

        var dimension = block.getDimension();
        var pos = block.getPosition();

        // Don't trigger if an activation is already scheduled (prevents duplicate updates)
        if (dimension.getBlockUpdateManager().hasScheduledBlockUpdate(pos)) {
            return;
        }

        // Schedule activation for 1 tick later (matches vanilla behavior)
        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, ACTIVATION_DELAY);
    }

    /**
     * Activates the observer, emitting a redstone pulse.
     * Called 1 tick after a block change is detected.
     */
    protected void activate(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        // Set powered state
        dimension.updateBlockProperty(POWERED_BIT, true, pos);

        // Update all neighbors including the observed face.
        // This allows face-to-face observers to form oscillators.
        // Infinite recursion is prevented by checks in onNeighborUpdate:
        // - Only responds to changes from the observed face direction
        // - Ignores if already powered or update already scheduled
        BlockFace outputFace = getOutputFace(block);
        dimension.updateAround(pos);
        dimension.updateAround(outputFace.offsetPos(pos));

        // Schedule deactivation for 2 ticks later
        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, PULSE_DURATION);
    }

    /**
     * Deactivates the observer, ending the redstone pulse.
     * Called 2 ticks after activation.
     */
    protected void deactivate(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        // Turn off
        dimension.updateBlockProperty(POWERED_BIT, false, pos);

        // Update all neighbors including the observed face.
        // This allows face-to-face observers to form oscillators.
        BlockFace outputFace = getOutputFace(block);
        dimension.updateAround(pos);
        dimension.updateAround(outputFace.offsetPos(pos));
    }

    @Override
    public void onScheduledUpdate(Block block) {
        if (block.getPropertyValue(POWERED_BIT)) {
            // Currently powered - this is the deactivation update
            deactivate(block);
        } else {
            // Not powered - this is the activation update (1 tick after change detected)
            activate(block);
        }
    }

    @Override
    public void onMoved(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        if (block.getPropertyValue(POWERED_BIT)) {
            // Observer is powered when moved - deactivate immediately.
            // The original scheduled deactivation is lost when the block moves,
            // so we must handle it here to prevent the observer from staying lit.
            deactivate(block);
            return;
        }

        // Observer is unpowered when moved - schedule activation
        if (!dimension.getBlockUpdateManager().hasScheduledBlockUpdate(pos)) {
            dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, ACTIVATION_DELAY);
        }
    }

    @Override
    public int getWeakPower(Block block, BlockFace face) {
        if (!block.getPropertyValue(POWERED_BIT)) {
            return 0;
        }

        // Output from the back only
        BlockFace outputFace = getOutputFace(block);
        if (face == outputFace) {
            return MAX_REDSTONE_POWER;
        }
        return 0;
    }

    @Override
    public int getStrongPower(Block block, BlockFace face) {
        // Observer provides strong power from the back
        return getWeakPower(block, face);
    }

    @Override
    public boolean isPowerSource() {
        return true;
    }

    /**
     * Gets the face that the observer is observing (front face).
     */
    protected BlockFace getObservedFace(Block block) {
        MinecraftFacingDirection facing = block.getPropertyValue(MINECRAFT_FACING_DIRECTION);
        return toBlockFace(facing);
    }

    /**
     * Gets the face that outputs redstone signal (back face).
     */
    protected BlockFace getOutputFace(Block block) {
        return getObservedFace(block).opposite();
    }

    protected MinecraftFacingDirection getPlacementFacing(PlayerInteractInfo placementInfo) {
        var player = placementInfo.player();
        var pitch = player.getLocation().pitch();

        // If looking up or down significantly, place vertically
        // Observer's observing face points in the direction the player is looking
        if (pitch > 45) {
            return MinecraftFacingDirection.DOWN; // Looking down, observer faces down
        } else if (pitch < -45) {
            return MinecraftFacingDirection.UP; // Looking up, observer faces up
        }

        // Horizontal placement based on yaw
        var yaw = player.getLocation().yaw();
        yaw = ((yaw % 360) + 360) % 360;

        if (yaw >= 315 || yaw < 45) {
            return MinecraftFacingDirection.SOUTH;
        } else if (yaw >= 45 && yaw < 135) {
            return MinecraftFacingDirection.WEST;
        } else if (yaw >= 135 && yaw < 225) {
            return MinecraftFacingDirection.NORTH;
        } else {
            return MinecraftFacingDirection.EAST;
        }
    }

    protected BlockFace toBlockFace(MinecraftFacingDirection direction) {
        return switch (direction) {
            case DOWN -> BlockFace.DOWN;
            case UP -> BlockFace.UP;
            case NORTH -> BlockFace.NORTH;
            case SOUTH -> BlockFace.SOUTH;
            case WEST -> BlockFace.WEST;
            case EAST -> BlockFace.EAST;
        };
    }
}
