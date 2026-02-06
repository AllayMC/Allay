package org.allaymc.server.block.component.tripwire;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.BlockNeighborUpdateContext;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.PowerSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.time.Duration;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * Implementation of tripwire hook block behavior.
 * <p>
 * Tripwire hooks connect to each other through tripwire strings (up to 42 blocks apart).
 * When an entity passes through the connected tripwire, the hooks emit a redstone signal.
 *
 * @author daoge_cmd
 */
public class BlockTripwireHookBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Maximum distance to search for a facing tripwire hook.
     */
    private static final int MAX_DISTANCE = 42;

    public BlockTripwireHookBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        BlockFace clickedFace = placementInfo.blockFace();

        // Tripwire hooks can only be placed on horizontal faces
        if (!clickedFace.isHorizontal()) {
            return false;
        }

        // Verify the attachment block has a full surface
        BlockFace attachedFace = clickedFace.opposite();
        Vector3ic attachedPos = attachedFace.offsetPos(placeBlockPos);
        BlockState attachedState = dimension.getBlockState(attachedPos);
        if (!attachedState.getBlockStateData().collisionShape().isFull(clickedFace)) {
            return false;
        }

        // Set direction based on the face the hook is placed on
        blockState = blockState.setPropertyValue(DIRECTION_4, clickedFace.getHorizontalIndex());
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        // Calculate connection state after placement
        Block newBlock = new Block(newBlockState, oldBlock.getPosition());
        calculateState(newBlock, false, false, -1, null);
    }

    @Override
    public void onNeighborUpdate(BlockNeighborUpdateContext context) {
        super.onNeighborUpdate(context);
        var block = context.block();

        // Check if the attached block still supports the hook
        BlockFace hookFacing = getHookFacing(block);
        if (hookFacing == null) {
            return;
        }

        BlockFace attachedFace = hookFacing.opposite();
        if (context.face() == attachedFace) {
            if (!context.neighbor().getBlockStateData().collisionShape().isFull(hookFacing)) {
                block.breakBlock();
            }
        }
    }

    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);

        // Only handle actual block removal, not property updates
        // When we update hook properties, newBlockState is still TRIPWIRE_HOOK
        if (newBlockState.getBlockType() == BlockTypes.TRIPWIRE_HOOK) {
            return;
        }

        boolean attached = oldBlock.getPropertyValue(ATTACHED_BIT);
        boolean powered = oldBlock.getPropertyValue(POWERED_BIT);

        if (attached || powered) {
            calculateState(oldBlock, true, false, -1, null);
        }

        if (powered) {
            Dimension dimension = oldBlock.getDimension();
            dimension.updateAround(oldBlock.getPosition());

            BlockFace hookFacing = getHookFacing(oldBlock);
            if (hookFacing != null) {
                dimension.updateAround(hookFacing.opposite().offsetPos(oldBlock.getPosition()));
            }
        }
    }

    @Override
    public void onScheduledUpdate(Block block) {
        calculateState(block, false, true, -1, null);
    }

    @Override
    public int getWeakPower(Block block, BlockFace face) {
        return block.getPropertyValue(POWERED_BIT) ? MAX_REDSTONE_POWER : 0;
    }

    @Override
    public int getStrongPower(Block block, BlockFace face) {
        if (!block.getPropertyValue(POWERED_BIT)) {
            return 0;
        }

        // Strong power goes to the attached block (behind the hook)
        // hookFacing is where the hook points (toward tripwire)
        // The attached block is in the opposite direction
        BlockFace hookFacing = getHookFacing(block);
        if (hookFacing != null && face == hookFacing.opposite()) {
            return MAX_REDSTONE_POWER;
        }
        return 0;
    }

    /**
     * Gets the facing direction of the tripwire hook.
     */
    private BlockFace getHookFacing(Block block) {
        int direction = block.getPropertyValue(DIRECTION_4);
        return BlockFace.fromHorizontalIndex(direction);
    }

    /**
     * Calculates and updates the tripwire connection state.
     *
     * @param block        the hook block
     * @param onBreak      whether this is called during block breaking
     * @param updateAround whether to send redstone updates to neighbors
     * @param pos          position along the line being updated (-1 for full scan)
     * @param wireBlock    specific wire block state at pos (null for auto-detect)
     */
    public void calculateState(Block block, boolean onBreak, boolean updateAround, int pos, Block wireBlock) {
        BlockFace hookFacing = getHookFacing(block);
        if (hookFacing == null) {
            return;
        }

        Dimension dimension = block.getDimension();
        Vector3ic hookPos = block.getPosition();

        boolean wasAttached = block.getPropertyValue(ATTACHED_BIT);
        boolean wasPowered = block.getPropertyValue(POWERED_BIT);
        boolean canConnect = !onBreak;
        boolean nextPowered = false;
        int distance = 0;
        Block[] wireBlocks = new Block[MAX_DISTANCE];

        // Scan for the opposite hook and collect tripwire blocks
        for (int i = 1; i < MAX_DISTANCE; i++) {
            Vector3ic scanPos = hookFacing.offsetPos(hookPos, i);
            Block scannedBlock = new Block(dimension, scanPos);
            BlockType<?> scannedType = scannedBlock.getBlockType();

            if (scannedType == BlockTypes.TRIPWIRE_HOOK) {
                // Found a hook - check if it faces opposite direction
                BlockFace otherFacing = getHookFacingFromState(scannedBlock);
                if (otherFacing == hookFacing.opposite()) {
                    distance = i;
                }
                break;
            }

            if (scannedType != BlockTypes.TRIP_WIRE && i != pos) {
                // Non-tripwire block breaks the connection
                wireBlocks[i] = null;
                canConnect = false;
            } else {
                if (i == pos && wireBlock != null) {
                    scannedBlock = wireBlock;
                }

                if (scannedType == BlockTypes.TRIP_WIRE) {
                    boolean disarmed = scannedBlock.getPropertyValue(DISARMED_BIT);
                    boolean wirePowered = scannedBlock.getPropertyValue(POWERED_BIT);
                    nextPowered |= (!disarmed && wirePowered);

                    if (i == pos) {
                        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(hookPos, Duration.ofMillis(100));
                        canConnect &= !disarmed;
                    }
                }
                wireBlocks[i] = scannedBlock;
            }
        }

        // Connection requires at least 1 block distance between hooks
        canConnect = canConnect && distance > 1;
        nextPowered = nextPowered && canConnect;

        // Update the opposite hook only if state changed
        if (distance > 0) {
            Vector3ic otherHookPos = hookFacing.offsetPos(hookPos, distance);
            BlockState otherHookState = dimension.getBlockState(otherHookPos);

            // Verify the opposite hook still exists
            if (otherHookState.getBlockType() != BlockTypes.TRIPWIRE_HOOK) {
                return;
            }

            // Only update if state actually changed to prevent infinite recursion
            boolean otherAttached = otherHookState.getPropertyValue(ATTACHED_BIT);
            boolean otherPowered = otherHookState.getPropertyValue(POWERED_BIT);

            if (otherAttached != canConnect || otherPowered != nextPowered) {
                BlockFace otherFacing = hookFacing.opposite();

                // Create updated state for opposite hook
                BlockState newOtherState = otherHookState
                        .setPropertyValue(ATTACHED_BIT, canConnect)
                        .setPropertyValue(POWERED_BIT, nextPowered);

                dimension.setBlockState(otherHookPos.x(), otherHookPos.y(), otherHookPos.z(), newOtherState);
                dimension.updateAround(otherHookPos);
                dimension.updateAround(otherFacing.opposite().offsetPos(otherHookPos));

                addSound(dimension, otherHookPos, canConnect, nextPowered, otherAttached, otherPowered);
            }
        }

        if (!onBreak) {
            // Only update if state actually changed to prevent infinite recursion
            if (wasAttached != canConnect || wasPowered != nextPowered) {
                // Play sound at current hook position
                addSound(dimension, hookPos, canConnect, nextPowered, wasAttached, wasPowered);

                // Update current hook state
                BlockState newState = block.getBlockState()
                        .setPropertyValue(ATTACHED_BIT, canConnect)
                        .setPropertyValue(POWERED_BIT, nextPowered);

                dimension.setBlockState(hookPos.x(), hookPos.y(), hookPos.z(), newState);

                if (updateAround) {
                    dimension.updateAround(hookPos);
                    dimension.updateAround(hookFacing.opposite().offsetPos(hookPos));
                }
            }
        }

        // Update tripwire attachment state if changed
        if (wasAttached != canConnect) {
            for (int i = 1; i < distance; i++) {
                Block wire = wireBlocks[i];
                if (wire != null && wire.getBlockType() == BlockTypes.TRIP_WIRE) {
                    Vector3ic wirePos = hookFacing.offsetPos(hookPos, i);
                    BlockState wireState = dimension.getBlockState(wirePos);

                    // Set attached bit to match the connection state
                    BlockState newWireState = wireState.setPropertyValue(ATTACHED_BIT, canConnect);
                    dimension.setBlockState(wirePos.x(), wirePos.y(), wirePos.z(), newWireState);
                }
            }
        }
    }

    /**
     * Gets the facing direction from a hook block state.
     */
    private BlockFace getHookFacingFromState(Block block) {
        int direction = block.getPropertyValue(DIRECTION_4);
        return BlockFace.fromHorizontalIndex(direction);
    }

    /**
     * Plays appropriate sound effects based on state changes.
     */
    private void addSound(Dimension dimension, Vector3ic pos, boolean canConnect, boolean nextPowered, boolean wasAttached, boolean wasPowered) {
        Block block = new Block(dimension, pos);

        if (nextPowered && !wasPowered) {
            block.addSound(new PowerSound(true));
        } else if (!nextPowered && wasPowered) {
            block.addSound(new PowerSound(false));
        } else if (canConnect && !wasAttached) {
            block.addSound(new PowerSound(true)); // Attach sound
        } else if (!canConnect && wasAttached) {
            block.addSound(new PowerSound(false)); // Detach sound
        }
    }
}
