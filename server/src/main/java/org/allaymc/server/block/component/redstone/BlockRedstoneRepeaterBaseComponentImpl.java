package org.allaymc.server.block.component.redstone;

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
 * {@code BlockRedstoneRepeaterBaseComponentImpl} specializes delayed diode behavior
 * with maximum-strength output and four selectable delay settings. Player interaction
 * cycles the stored delay, while powered diode sources on either side can lock
 * transitions. Variant replacement preserves both facing and delay so subsequent
 * scheduled callbacks retain the repeater's configured timing.
 *
 * @author daoge_cmd
 */
public class BlockRedstoneRepeaterBaseComponentImpl extends BlockRedstoneDiodeBaseComponentImpl {

    /**
     * Creates a repeater component with the supplied fixed powered-variant identity.
     */
    public BlockRedstoneRepeaterBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean powered) {
        super(blockType, powered);
    }

    /**
     * Cycles the clicked repeater's delay setting unless inherited handling consumes
     * the action. A {@code null} {@code interactInfo} returns {@code false}; other
     * interactions request the next setting and return {@code true}.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (interactInfo == null) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        int currentDelay = blockState.getPropertyValue(REPEATER_DELAY);
        // The four encoded levels wrap after the longest selectable repeater delay.
        int newDelay = (currentDelay + 1) % 4;

        dimension.updateBlockProperty(REPEATER_DELAY, newDelay, pos);
        return true;
    }

    /**
     * Returns a single repeater item regardless of the registered powered variant.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.REPEATER.createItemStack());
    }

    /**
     * Returns whether a side diode supplies positive power toward this repeater. Both
     * repeaters and comparators can contribute through the inherited side-source
     * lookup, and a positive result suppresses delayed transitions.
     */
    @Override
    protected boolean isLocked(Block block) {
        return getPowerOnSides(block) > 0;
    }

    /**
     * Returns {@code BASE_DELAY} multiplied by one more than the stored delay level.
     * Levels {@code 0} through {@code 3} therefore select one through four redstone
     * ticks for scheduled transitions.
     */
    @Override
    protected Duration getDelay(Block block) {
        int delayLevel = block.getPropertyValue(REPEATER_DELAY);
        // Stored level zero still represents one redstone tick of delay.
        return BASE_DELAY.multipliedBy(delayLevel + 1);
    }

    /**
     * Returns maximum output strength for the inherited directional power queries.
     */
    @Override
    protected int getRedstoneSignal(Block block) {
        return MAX_REDSTONE_POWER;
    }

    /**
     * Requests the selected powered or unpowered repeater type while preserving facing
     * and delay. Output-side neighbor notifications follow the request even when the
     * dimension does not accept the replacement.
     */
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

        BlockFace outputFace = getFacing(block);
        updateNeighborsOnStateChange(dimension, pos, outputFace);
    }
}
