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
import org.allaymc.api.world.Dimension;

import java.time.Duration;
import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.REPEATER_DELAY;

/**
 * Implementation of the redstone repeater block.
 * Delays and repeats redstone signals in one direction.
 * Can be locked by another repeater pointing into its side.
 *
 * @author daoge_cmd
 */
public class BlockRedstoneRepeaterBaseComponentImpl extends BlockRedstoneDiodeBaseComponentImpl {

    public BlockRedstoneRepeaterBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean powered) {
        super(blockType, powered);
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
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.REPEATER.createItemStack());
    }

    @Override
    protected boolean isLocked(Block block) {
        return getPowerOnSides(block) > 0;
    }

    @Override
    protected Duration getDelay(Block block) {
        int delayLevel = block.getPropertyValue(REPEATER_DELAY);
        // delay 0 = 1 redstone tick, delay 1 = 2 redstone ticks, etc.
        return BASE_DELAY.multipliedBy(delayLevel + 1);
    }

    @Override
    protected int getRedstoneSignal(Block block) {
        return MAX_REDSTONE_POWER;
    }

    @Override
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
        BlockFace outputFace = getFacing(block);
        updateNeighborsOnStateChange(dimension, pos, outputFace);
    }
}
