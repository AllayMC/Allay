package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.time.Duration;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.REDSTONE_SIGNAL;

/**
 * Base implementation for redstone diode blocks (repeater, comparator).
 * Provides common functionality for directional signal processing.
 *
 * @author daoge_cmd
 */
public abstract class BlockRedstoneDiodeBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final Duration BASE_DELAY = Duration.ofMillis(100); // 2 game ticks = 1 redstone tick

    protected final boolean powered;

    public BlockRedstoneDiodeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean powered) {
        super(blockType);
        this.powered = powered;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        // Diode requires solid block below
        BlockState below = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!below.getBlockStateData().isSolid()) {
            return false;
        }

        // Set facing direction based on player (property stores the input/back direction)
        if (placementInfo != null) {
            BlockFace playerFacing = placementInfo.player().getHorizontalFace().opposite();
            blockState = blockState.setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, playerFacing.toMinecraftCardinalDirection());
        }

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        // Check if should be powered immediately after placement
        var newBlock = new Block(newBlockState, oldBlock.getPosition());
        if (shouldBePowered(newBlock)) {
            scheduleUpdate(newBlock);
        }
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        // Break if block below is removed
        if (face == BlockFace.DOWN && !neighbor.getBlockStateData().isSolid()) {
            block.breakBlock();
            return;
        }

        // Update state if not locked
        if (!isLocked(block)) {
            updateState(block);
        }
    }

    /**
     * Called when neighbor updates to check if state should change.
     */
    protected void updateState(Block block) {
        boolean shouldBePowered = shouldBePowered(block);
        if (powered != shouldBePowered) {
            scheduleUpdate(block);
        }
    }

    @Override
    public void onScheduledUpdate(Block block) {
        if (isLocked(block)) {
            return;
        }

        boolean shouldBePowered = shouldBePowered(block);

        if (powered && !shouldBePowered) {
            switchState(block, false);
        } else if (!powered && shouldBePowered) {
            switchState(block, true);
        }
    }

    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);

        // When removed, update neighbors if was powered
        if (powered) {
            var dimension = oldBlock.getDimension();
            var pos = oldBlock.getPosition();

            dimension.updateAround(pos);

            // Update the block in front (where power was output)
            BlockFace outputFace = getFacing(oldBlock);
            dimension.updateAround(outputFace.offsetPos(pos));
        }
    }

    @Override
    public int getWeakPower(Block block, BlockFace face) {
        if (!powered) {
            return 0;
        }

        // Only output power in the direction it's facing (front)
        BlockFace outputFace = getFacing(block);
        if (face == outputFace) {
            return getRedstoneSignal(block);
        }

        return 0;
    }

    @Override
    public int getStrongPower(Block block, BlockFace face) {
        return getWeakPower(block, face);
    }

    @Override
    public boolean isPowerSource() {
        return true;
    }

    /**
     * Gets the direction the diode is facing (output direction).
     * Note: MINECRAFT_CARDINAL_DIRECTION stores the input direction, so we need opposite.
     */
    protected BlockFace getFacing(Block block) {
        MinecraftCardinalDirection direction = block.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        return BlockFace.from(direction).opposite();
    }

    /**
     * Gets the input face (back of the diode).
     */
    protected BlockFace getInputFace(Block block) {
        MinecraftCardinalDirection direction = block.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        return BlockFace.from(direction);
    }

    /**
     * Checks if the diode should be powered based on input.
     */
    protected boolean shouldBePowered(Block block) {
        return calculateInputStrength(block) > 0;
    }

    /**
     * Calculates the input signal strength from the back of the diode.
     */
    protected int calculateInputStrength(Block block) {
        BlockFace inputFace = getInputFace(block);
        Vector3ic inputPos = inputFace.offsetPos(block.getPosition());

        var dimension = block.getDimension();
        BlockState inputState = dimension.getBlockState(inputPos);
        Block inputBlock = new Block(inputState, new Position3i(inputPos, dimension));

        // Get power from input position
        int power = inputState.getBehavior().getWeakPower(inputBlock, inputFace.opposite());

        // Also check for redstone wire directly
        if (inputState.getBlockType() == BlockTypes.REDSTONE_WIRE) {
            int wireSignal = inputState.getPropertyValue(REDSTONE_SIGNAL);
            power = Math.max(power, wireSignal);
        }

        // Check strong power through solid block
        if (power < MAX_REDSTONE_POWER && inputState.getBlockStateData().isSolid()) {
            int strongPower = dimension.getStrongPowerAt(new Position3i(inputPos, dimension), inputFace.opposite());
            power = Math.max(power, strongPower);
        }

        return power;
    }

    /**
     * Checks if the diode is locked (only applicable to repeaters).
     */
    protected boolean isLocked(Block block) {
        return false;
    }

    /**
     * Gets power from diodes on the sides.
     */
    protected int getPowerOnSides(Block block) {
        BlockFace facing = getFacing(block);
        BlockFace left = facing.rotateYCCW();
        BlockFace right = facing.rotateY();

        int leftPower = getPowerFromSide(block, left);
        int rightPower = getPowerFromSide(block, right);

        return Math.max(leftPower, rightPower);
    }

    /**
     * Gets power from a specific side (only from other repeaters/comparators).
     */
    protected int getPowerFromSide(Block block, BlockFace side) {
        Vector3ic sidePos = side.offsetPos(block.getPosition());
        var dimension = block.getDimension();
        BlockState sideState = dimension.getBlockState(sidePos);

        // Only consider other diodes
        if (!isDiode(sideState)) {
            return 0;
        }

        Block sideBlock = new Block(sideState, new Position3i(sidePos, dimension));
        return sideState.getBehavior().getStrongPower(sideBlock, side.opposite());
    }

    /**
     * Checks if a block is a diode (repeater or comparator).
     */
    protected boolean isDiode(BlockState state) {
        var type = state.getBlockType();
        return type == BlockTypes.POWERED_REPEATER ||
               type == BlockTypes.UNPOWERED_REPEATER ||
               type == BlockTypes.POWERED_COMPARATOR ||
               type == BlockTypes.UNPOWERED_COMPARATOR;
    }

    /**
     * Schedules a delayed update for the diode.
     */
    protected void scheduleUpdate(Block block) {
        block.scheduleUpdateInDelay(getDelay(block));
    }

    /**
     * Updates neighboring blocks after state change.
     */
    protected void updateNeighborsOnStateChange(Dimension dimension, Vector3ic pos, BlockFace outputFace) {
        dimension.updateAround(pos);
        dimension.updateAround(outputFace.offsetPos(pos));
    }

    /**
     * Gets the delay for this diode.
     */
    protected abstract Duration getDelay(Block block);

    /**
     * Gets the redstone signal output strength.
     */
    protected abstract int getRedstoneSignal(Block block);

    /**
     * Switches the diode between powered and unpowered states.
     */
    protected abstract void switchState(Block block, boolean newPowered);
}
