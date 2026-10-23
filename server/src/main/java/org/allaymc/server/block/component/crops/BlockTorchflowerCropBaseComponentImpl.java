package org.allaymc.server.block.component.crops;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.event.CBlockRandomUpdateEvent;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.GROWTH;

/**
 * {@code BlockTorchflowerCropBaseComponentImpl} implements the two crop stages preceding a
 * torchflower block. Bone meal and natural updates advance the crop once, then propose
 * replacement with the flower after the maximum crop stage has been reached.
 *
 * <p>
 * Natural growth retains the farmland crop probability and low-light fade behavior, while this
 * override dispatches the component update directly to avoid the base crop's seven-stage
 * advance. Each stage or flower replacement is cancellable and accepts the event's state.
 * Ordinary crop drops always yield torchflower seeds; the fully transformed flower uses its
 * own block behavior instead of this crop component.
 *
 * @author daoge_cmd
 */
public class BlockTorchflowerCropBaseComponentImpl extends BlockCropsBaseComponentImpl {

    /**
     * {@code MAX_GROWTH} marks the final torchflower crop stage before a later accepted growth
     * proposal creates the flower.
     */
    public static final int MAX_GROWTH = 1;

    /**
     * Creates the torchflower crop behavior component for the supplied block type.
     */
    public BlockTorchflowerCropBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Proposes the next crop stage or the final torchflower replacement. A crop at {@code
     * MAX_GROWTH} becomes a flower proposal rather than rejecting bone meal; cancellation
     * returns {@code false}, and acceptance writes the event's state.
     */
    @Override
    protected boolean onBoneMealUsed(Dimension dimension, Vector3ic pos, BlockState crop) {
        var growth = crop.getPropertyValue(GROWTH);
        if (growth >= MAX_GROWTH) {
            var newState = BlockTypes.TORCHFLOWER.getDefaultState();
            var event = new BlockGrowEvent(new Block(crop, new Position3i(pos, dimension)), newState);
            if (event.call()) {
                dimension.setBlockState(pos, event.getNewBlockState());
                return true;
            }
            return false;
        }

        var newCrop = crop.setPropertyValue(GROWTH, growth + 1);
        var event = new BlockGrowEvent(new Block(crop, new Position3i(pos, dimension)), newCrop);
        if (event.call()) {
            dimension.setBlockState(pos, event.getNewBlockState());
            return true;
        }

        return false;
    }

    /**
     * Dispatches the component random-update event and chooses low-light breaking or a growth
     * proposal. Internal light below eight raises a fade event; acceptance breaks the crop
     * instead of using that event's replacement state.
     *
     * <p>
     * With adequate light, both immature and final crop stages use the inherited soil-growth
     * probability. An immature crop proposes one stage of progress, while a final-stage crop
     * proposes a torchflower block. The growth event decides cancellation and the state
     * written at the original crop position.
     */
    @Override
    public void onRandomUpdate(Block block) {
        manager.callEvent(new CBlockRandomUpdateEvent(block));

        // Low light prevents both stage progression and flower transformation.
        if (block.getDimension().getLightEngine().getInternalLight(block.getPosition()) < 8) {
            var event = new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                block.breakBlock();
            }
        } else {
            var growth = block.getPropertyValue(GROWTH);
            if (growth < MAX_GROWTH) {
                if (ThreadLocalRandom.current().nextFloat() <= calculateGrowthChance(block)) {
                    var newCrop = block.setPropertyValue(GROWTH, growth + 1);
                    var event = new BlockGrowEvent(block, newCrop.getBlockState());
                    if (event.call()) {
                        block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
                    }
                }
            } else {
                if (ThreadLocalRandom.current().nextFloat() <= calculateGrowthChance(block)) {
                    var newState = BlockTypes.TORCHFLOWER.getDefaultState();
                    var event = new BlockGrowEvent(block, newState);
                    if (event.call()) {
                        block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
                    }
                }
            }
        }
    }

    /**
     * Returns one torchflower-seed item for breaking either unfinished crop stage.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.TORCHFLOWER_SEEDS.createItemStack());
    }
}
