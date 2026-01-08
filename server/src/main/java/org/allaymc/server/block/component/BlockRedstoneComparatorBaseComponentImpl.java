package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.interfaces.BlockEntityComparator;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.Dependency;

import java.time.Duration;
import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * Implementation of the redstone comparator block.
 * Has two modes: compare (outputs input if input >= side) and subtract (outputs input - side).
 *
 * @author daoge_cmd
 */
public class BlockRedstoneComparatorBaseComponentImpl extends BlockRedstoneDiodeBaseComponentImpl {

    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityComparator> blockEntityHolderComponent;

    public BlockRedstoneComparatorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean powered) {
        super(blockType, powered);
    }

    /**
     * Gets the block entity for this comparator.
     * The block entity is automatically created by BlockBlockEntityHolderComponent.
     */
    protected BlockEntityComparator getBlockEntity(Block block) {
        return blockEntityHolderComponent.getBlockEntity(block.getPosition());
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (interactInfo == null) {
            return false;
        }

        // Toggle between compare and subtract modes
        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        boolean isSubtractMode = blockState.getPropertyValue(OUTPUT_SUBTRACT_BIT);

        dimension.updateBlockProperty(OUTPUT_SUBTRACT_BIT, !isSubtractMode, pos);

        // Trigger update to recalculate output
        var block = new Block(dimension.getBlockState(pos), new Position3i(pos, dimension));
        updateState(block);

        return true;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.COMPARATOR.createItemStack());
    }

    @Override
    protected void updateState(Block block) {
        // Only schedule if there's no pending update already
        var manager = block.getDimension().getBlockUpdateManager();
        if (manager.hasScheduledBlockUpdate(block.getPosition())) {
            return;
        }

        // Check if output would change
        int newOutput = calculateOutput(block);
        int currentOutput = getBlockEntity(block).getOutputSignal();

        // Schedule update if output changed
        if (newOutput != currentOutput) {
            scheduleUpdate(block);
        }
    }

    @Override
    public void onScheduledUpdate(Block block) {
        int output = calculateOutput(block);
        var blockEntity = getBlockEntity(block);
        int currentOutput = blockEntity.getOutputSignal();
        boolean shouldBePowered = output > 0;

        // Update block entity with new output value
        blockEntity.setOutputSignal(output);

        if (powered && !shouldBePowered) {
            switchState(block, false, output);
        } else if (!powered && shouldBePowered) {
            switchState(block, true, output);
        } else if (powered && output != currentOutput) {
            // Only notify neighbors if output VALUE actually changed
            // This prevents infinite update loops when output is stable
            notifyOutputNeighbors(block);
        }
    }

    /**
     * Notifies neighbors that the output may have changed.
     */
    protected void notifyOutputNeighbors(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        BlockFace outputFace = getFacing(block);
        updateNeighborsOnStateChange(dimension, pos, outputFace);
    }

    @Override
    protected boolean shouldBePowered(Block block) {
        // Comparator is powered when its output is greater than 0
        return calculateOutput(block) > 0;
    }

    /**
     * Calculates the output signal based on mode.
     */
    protected int calculateOutput(Block block) {
        int input = calculateInputStrength(block);
        int sidePower = getPowerOnSides(block);

        if (isSubtractMode(block)) {
            return Math.max(0, input - sidePower);
        } else {
            // Compare mode: output input if input >= side power
            return input >= sidePower ? input : 0;
        }
    }

    @Override
    protected int getPowerFromSide(Block block, BlockFace side) {
        var sidePos = side.offsetPos(block.getPosition());
        var dimension = block.getDimension();
        BlockState sideState = dimension.getBlockState(sidePos);

        // Comparator accepts power from any power source on sides (not just diodes)
        if (!sideState.getBehavior().isPowerSource()) {
            return 0;
        }

        // Special case for redstone wire - read signal directly
        if (sideState.getBlockType() == BlockTypes.REDSTONE_WIRE) {
            return sideState.getPropertyValue(REDSTONE_SIGNAL);
        }

        // Special case for redstone block
        if (sideState.getBlockType() == BlockTypes.REDSTONE_BLOCK) {
            return MAX_REDSTONE_POWER;
        }

        // For other power sources (including diodes), get strong power
        Block sideBlock = new Block(sideState, new Position3i(sidePos, dimension));
        return sideState.getBehavior().getStrongPower(sideBlock, side.opposite());
    }

    /**
     * Checks if the comparator is in subtract mode.
     */
    protected boolean isSubtractMode(Block block) {
        return block.getPropertyValue(OUTPUT_SUBTRACT_BIT);
    }

    @Override
    protected Duration getDelay(Block block) {
        // Comparator has fixed delay of 1 redstone tick
        return BASE_DELAY;
    }

    @Override
    protected int getRedstoneSignal(Block block) {
        // Get output from block entity
        return getBlockEntity(block).getOutputSignal();
    }

    @Override
    protected void switchState(Block block, boolean newPowered) {
        switchState(block, newPowered, calculateOutput(block));
    }

    /**
     * Switches state with specific output signal.
     */
    protected void switchState(Block block, boolean newPowered, int outputSignal) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        MinecraftCardinalDirection direction = block.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        boolean subtractMode = block.getPropertyValue(OUTPUT_SUBTRACT_BIT);
        boolean outputLit = outputSignal > 0;

        BlockType<?> newType = newPowered ? BlockTypes.POWERED_COMPARATOR : BlockTypes.UNPOWERED_COMPARATOR;
        BlockState newState = newType.getDefaultState()
                .setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, direction)
                .setPropertyValue(OUTPUT_SUBTRACT_BIT, subtractMode)
                .setPropertyValue(OUTPUT_LIT_BIT, outputLit);

        dimension.setBlockState(pos.x(), pos.y(), pos.z(), newState);

        // Update the block entity's output signal
        getBlockEntity(new Block(newState, pos)).setOutputSignal(outputSignal);

        // Update neighbors
        BlockFace outputFace = getFacing(block);
        updateNeighborsOnStateChange(dimension, pos, outputFace);
    }
}
