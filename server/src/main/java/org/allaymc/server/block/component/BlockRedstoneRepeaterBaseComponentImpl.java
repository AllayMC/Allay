package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.RedstoneHelper;
import org.joml.Vector3ic;

import java.time.Duration;
import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * Implementation of the redstone repeater block.
 * Delays and repeats redstone signals in one direction.
 * Can be locked by another repeater pointing into its side.
 *
 * @author daoge_cmd
 */
public class BlockRedstoneRepeaterBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final Duration BASE_DELAY = Duration.ofMillis(100); // 2 game ticks = 1 redstone tick

    protected final boolean powered;

    public BlockRedstoneRepeaterBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean powered) {
        super(blockType);
        this.powered = powered;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        // Repeater requires solid block below
        BlockState below = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!below.getBlockStateData().isSolid()) {
            return false;
        }

        // Set facing direction based on player (property stores the input/back direction)
        if (placementInfo != null) {
            BlockFace playerFacing = placementInfo.player().getHorizontalFace().opposite();
            blockState = blockState.setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, playerFacing.toMinecraftCardinalDirection());
        }

        if (!dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo)) {
            return false;
        }

        return true;
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
            boolean shouldBePowered = shouldBePowered(block);
            if (powered != shouldBePowered) {
                scheduleUpdate(block);
            }
        }
    }

    @Override
    public void onScheduledUpdate(Block block) {
        if (isLocked(block)) {
            return;
        }

        boolean shouldBePowered = shouldBePowered(block);

        if (powered && !shouldBePowered) {
            // Turn off
            switchState(block, false);
        } else if (!powered && shouldBePowered) {
            // Turn on
            switchState(block, true);
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (interactInfo == null) {
            return false;
        }

        // Cycle through delay settings (0-3)
        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        int currentDelay = blockState.getPropertyValue(REPEATER_DELAY);
        int newDelay = (currentDelay + 1) % 4;

        dimension.updateBlockProperty(REPEATER_DELAY, newDelay, pos);
        return true;
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
        // face parameter is the direction from repeater to querying block
        BlockFace outputFace = getFacing(block);
        if (face == outputFace) {
            return MAX_REDSTONE_POWER;
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

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.REPEATER.createItemStack());
    }

    /**
     * Gets the direction the repeater is facing (output direction).
     * Note: MINECRAFT_CARDINAL_DIRECTION stores the input direction, so we need opposite.
     */
    protected BlockFace getFacing(Block block) {
        MinecraftCardinalDirection direction = block.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        return BlockFace.from(direction).opposite();
    }

    /**
     * Gets the input face (back of the repeater).
     * This is the direction stored in MINECRAFT_CARDINAL_DIRECTION.
     */
    protected BlockFace getInputFace(Block block) {
        MinecraftCardinalDirection direction = block.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        return BlockFace.from(direction);
    }

    /**
     * Checks if the repeater should be powered based on input.
     */
    protected boolean shouldBePowered(Block block) {
        return calculateInputStrength(block) > 0;
    }

    /**
     * Calculates the input signal strength from the back of the repeater.
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
        if (power < MAX_REDSTONE_POWER && inputState.getBlockStateData().isOpaqueSolid()) {
            int strongPower = RedstoneHelper.getStrongPowerAt(new Position3i(inputPos, dimension), inputFace.opposite());
            power = Math.max(power, strongPower);
        }

        return power;
    }

    /**
     * Checks if the repeater is locked by another repeater on its side.
     */
    protected boolean isLocked(Block block) {
        return getPowerOnSides(block) > 0;
    }

    /**
     * Gets power from repeaters on the sides.
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

        // Only locked by other diodes (repeaters and comparators)
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
     * Gets the delay in milliseconds based on the delay property.
     * Each delay level adds 1 redstone tick (2 game ticks = 100ms).
     */
    protected Duration getDelay(Block block) {
        int delayLevel = block.getPropertyValue(REPEATER_DELAY);
        // delay 0 = 1 redstone tick, delay 1 = 2 redstone ticks, etc.
        return BASE_DELAY.multipliedBy(delayLevel + 1);
    }

    protected void scheduleUpdate(Block block) {
        block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(
                block.getPosition(), getDelay(block)
        );
    }

    protected void switchState(Block block, boolean newPowered) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        MinecraftCardinalDirection direction = block.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        int delay = block.getPropertyValue(REPEATER_DELAY);

        BlockType<?> newType = newPowered ? BlockTypes.POWERED_REPEATER : BlockTypes.UNPOWERED_REPEATER;
        BlockState newState = newType.getDefaultState()
                .setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, direction)
                .setPropertyValue(REPEATER_DELAY, delay);

        dimension.setBlockState(pos.x(), pos.y(), pos.z(), newState);

        // Update neighbors
        dimension.updateAround(pos);

        // Update the block in front (where power is output)
        // direction property stores the input face, so output is opposite
        BlockFace inputFace = BlockFace.from(direction);
        BlockFace outputFace = inputFace.opposite();
        dimension.updateAround(outputFace.offsetPos(pos));
    }
}
