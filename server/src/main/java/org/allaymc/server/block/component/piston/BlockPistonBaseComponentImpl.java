package org.allaymc.server.block.component.piston;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.interfaces.BlockEntityPistonArm;
import org.allaymc.api.eventbus.event.block.BlockPistonEvent;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;

/**
 * Base component implementation for piston and sticky piston blocks.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockPistonBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Duration of the piston extension/retraction animation (2 game ticks = 100ms).
     */
    protected static final Duration ANIMATION_DURATION = Duration.ofMillis(100);

    public BlockPistonBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            // Calculate facing direction based on player position
            int facing = getPlacementFacing(placeBlockPos, placementInfo);
            blockState = blockState.setPropertyValue(FACING_DIRECTION, facing);
        }

        boolean result = dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                blockState, placementInfo
        );

        if (result) {
            // Note: Block entity is automatically created by BlockBlockEntityHolderComponent
            // We need to initialize the piston arm properties after it's created
            var pistonArm = (BlockEntityPistonArm) dimension.getBlockEntity(placeBlockPos);
            if (pistonArm != null) {
                pistonArm.setSticky(isSticky());
                pistonArm.setFacing(getPistonFace(blockState));
            }

            // Check if we should extend immediately due to redstone power
            Block block = new Block(dimension, placeBlockPos);
            if (hasRedstonePower(block)) {
                // Schedule extension for next tick
                dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(placeBlockPos, Duration.ofMillis(50));
            }
        }

        return result;
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        // Schedule a check for next tick to handle redstone updates
        block.scheduleUpdateInDelay(Duration.ofMillis(50));
    }

    @Override
    public void onScheduledUpdate(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        var blockState = block.getBlockState();

        boolean isPowered = hasRedstonePower(block);
        boolean isExtended = isExtended(dimension, pos, blockState);

        if (isPowered && !isExtended) {
            // Try to extend
            extend(block);
        } else if (!isPowered && isExtended) {
            // Try to retract
            retract(block);
        }
    }

    /**
     * Attempt to extend the piston.
     *
     * @param block The piston block
     * @return true if extension started successfully
     */
    protected boolean extend(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        var blockState = block.getBlockState();
        BlockFace facing = getPistonFace(blockState);

        // Calculate what blocks need to be moved
        PistonPushCalculator calculator = new PistonPushCalculator(
                dimension, pos, facing, isSticky(), true
        );

        if (!calculator.calculate()) {
            // Cannot extend - something is blocking
            return false;
        }

        // Create event
        List<Vector3ic> blocksToMove = new ArrayList<>(calculator.getBlocksToMove());
        List<Vector3ic> blocksToDestroy = new ArrayList<>(calculator.getBlocksToDestroy());
        Map<Vector3ic, BlockState> originalStates = new HashMap<>(calculator.getOriginalStates());

        BlockPistonEvent event = new BlockPistonEvent(
                block, facing, blocksToMove, blocksToDestroy, originalStates, true, isSticky()
        );

        if (Server.getInstance().getEventBus().callEvent(event).isCancelled()) {
            return false;
        }

        // Execute the push
        executePush(dimension, pos, facing, blocksToMove, blocksToDestroy, originalStates);

        // Play sound
        dimension.addSound(pos.x() + 0.5, pos.y() + 0.5, pos.z() + 0.5, SimpleSound.PISTON_PUSH);

        return true;
    }

    /**
     * Attempt to retract the piston.
     *
     * @param block The piston block
     * @return true if retraction started successfully
     */
    protected boolean retract(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        var blockState = block.getBlockState();
        BlockFace facing = getPistonFace(blockState);

        // For sticky pistons, calculate what blocks to pull
        List<Vector3ic> blocksToMove = new ArrayList<>();
        List<Vector3ic> blocksToDestroy = new ArrayList<>();
        Map<Vector3ic, BlockState> originalStates = new HashMap<>();

        if (isSticky()) {
            PistonPushCalculator calculator = new PistonPushCalculator(
                    dimension, pos, facing, true, false
            );

            if (calculator.calculate()) {
                blocksToMove.addAll(calculator.getBlocksToMove());
                blocksToDestroy.addAll(calculator.getBlocksToDestroy());
                originalStates.putAll(calculator.getOriginalStates());
            }
        }

        // Create event
        BlockPistonEvent event = new BlockPistonEvent(
                block, facing, blocksToMove, blocksToDestroy, originalStates, false, isSticky()
        );

        if (Server.getInstance().getEventBus().callEvent(event).isCancelled()) {
            return false;
        }

        // Execute the retraction
        executeRetract(dimension, pos, facing, blocksToMove, blocksToDestroy, originalStates);

        // Play sound
        dimension.addSound(pos.x() + 0.5, pos.y() + 0.5, pos.z() + 0.5, SimpleSound.PISTON_PULL);

        return true;
    }

    /**
     * Execute the push operation.
     */
    protected void executePush(
            Dimension dimension,
            Vector3ic pistonPos,
            BlockFace facing,
            List<Vector3ic> blocksToMove,
            List<Vector3ic> blocksToDestroy,
            Map<Vector3ic, BlockState> originalStates
    ) {
        // 1. Destroy blocks that should be destroyed
        for (Vector3ic pos : blocksToDestroy) {
            BlockState state = originalStates.get(pos);
            if (state != null) {
                // Break the block (drop items)
                dimension.breakBlock(pos, null, null);
            }
        }

        // 2. Clear the original positions of blocks to move (from back to front)
        for (int i = blocksToMove.size() - 1; i >= 0; i--) {
            Vector3ic pos = blocksToMove.get(i);
            dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());
        }

        // 3. Place blocks in their new positions (from front to back)
        for (int i = 0; i < blocksToMove.size(); i++) {
            Vector3ic oldPos = blocksToMove.get(i);
            Vector3ic newPos = facing.offsetPos(oldPos);
            BlockState state = originalStates.get(oldPos);

            if (state != null) {
                // Create moving block at new position
                dimension.setBlockState(newPos, BlockTypes.MOVING_BLOCK.getDefaultState());
                // TODO: Create MovingBlock block entity to store the original block state
            }
        }

        // 4. Create the piston arm at the extended position
        Vector3ic armPos = facing.offsetPos(pistonPos);
        BlockType<?> armType = isSticky()
                ? BlockTypes.STICKY_PISTON_ARM_COLLISION
                : BlockTypes.PISTON_ARM_COLLISION;
        dimension.setBlockState(armPos, armType.getDefaultState()
                .setPropertyValue(FACING_DIRECTION, facing.ordinal()));

        // 5. Update piston arm block entity state
        var pistonArmBlockEntity = dimension.getBlockEntity(pistonPos);
        if (pistonArmBlockEntity instanceof BlockEntityPistonArm pistonArm) {
            pistonArm.startExtending(blocksToMove, originalStates);
        }

        // 6. Schedule animation completion
        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pistonPos, ANIMATION_DURATION);
    }

    /**
     * Execute the retract operation.
     */
    protected void executeRetract(
            Dimension dimension,
            Vector3ic pistonPos,
            BlockFace facing,
            List<Vector3ic> blocksToMove,
            List<Vector3ic> blocksToDestroy,
            Map<Vector3ic, BlockState> originalStates
    ) {
        // 1. Destroy blocks that should be destroyed
        for (Vector3ic pos : blocksToDestroy) {
            BlockState state = originalStates.get(pos);
            if (state != null) {
                dimension.breakBlock(pos, null, null);
            }
        }

        // 2. For sticky piston, move blocks
        if (isSticky() && !blocksToMove.isEmpty()) {
            // Clear original positions
            for (Vector3ic pos : blocksToMove) {
                dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());
            }

            // Place at new positions (one closer to piston)
            BlockFace pullDirection = facing.opposite();
            for (Vector3ic oldPos : blocksToMove) {
                Vector3ic newPos = pullDirection.offsetPos(oldPos);
                BlockState state = originalStates.get(oldPos);
                if (state != null) {
                    dimension.setBlockState(newPos, state);
                }
            }
        }

        // 3. Remove the piston arm
        Vector3ic armPos = facing.offsetPos(pistonPos);
        dimension.setBlockState(armPos, BlockTypes.AIR.getDefaultState());

        // 4. Update piston arm block entity state
        var pistonArmBlockEntity = dimension.getBlockEntity(pistonPos);
        if (pistonArmBlockEntity instanceof BlockEntityPistonArm pistonArm) {
            pistonArm.startRetracting();
        }

        // 5. Schedule animation completion
        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pistonPos, ANIMATION_DURATION);
    }

    /**
     * Check if the piston has redstone power.
     */
    protected boolean hasRedstonePower(Block block) {
        BlockFace pistonFace = getPistonFace(block.getBlockState());

        // Check power from all sides except the face the piston is pointing
        return block.getPower() > 0 || block.getStrongPower(pistonFace) > 0;
    }

    /**
     * Check if the piston is currently extended.
     */
    protected boolean isExtended(Dimension dimension, Vector3ic pistonPos, BlockState pistonState) {
        BlockFace facing = getPistonFace(pistonState);
        Vector3ic armPos = facing.offsetPos(pistonPos);
        BlockState armState = dimension.getBlockState(armPos);
        BlockType<?> armType = armState.getBlockType();

        return armType == BlockTypes.PISTON_ARM_COLLISION ||
               armType == BlockTypes.STICKY_PISTON_ARM_COLLISION;
    }

    /**
     * Get the facing direction of the piston.
     */
    protected BlockFace getPistonFace(BlockState blockState) {
        int facing = blockState.getPropertyValue(FACING_DIRECTION);
        return BlockFace.fromIndex(facing);
    }

    /**
     * Calculate the facing direction when placing the piston.
     */
    protected int getPlacementFacing(Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var player = placementInfo.player();
        var playerPos = player.getLocation();

        // Calculate the horizontal distance
        double dx = playerPos.x() - (placeBlockPos.x() + 0.5);
        double dz = playerPos.z() - (placeBlockPos.z() + 0.5);
        double horizontalDist = Math.sqrt(dx * dx + dz * dz);

        // If player is very close, use vertical facing based on eye position
        if (horizontalDist < 2.0) {
            double dy = playerPos.y() + player.getEyeHeight() - (placeBlockPos.y() + 0.5);

            if (dy > 2.0) {
                return BlockFace.UP.ordinal();
            } else if (dy < 0) {
                return BlockFace.DOWN.ordinal();
            }
        }

        // Use horizontal facing based on player direction
        return player.getHorizontalFace().ordinal();
    }

    /**
     * Whether this piston is sticky.
     */
    protected boolean isSticky() {
        return false;
    }
}
