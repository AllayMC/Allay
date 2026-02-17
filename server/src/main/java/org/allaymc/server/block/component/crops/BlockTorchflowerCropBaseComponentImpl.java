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
 * @author daoge_cmd
 */
public class BlockTorchflowerCropBaseComponentImpl extends BlockCropsBaseComponentImpl {

    public static final int MAX_GROWTH = 1;

    public BlockTorchflowerCropBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    protected boolean onBoneMealUsed(Dimension dimension, Vector3ic pos, BlockState crop) {
        var growth = crop.getPropertyValue(GROWTH);
        if (growth >= MAX_GROWTH) {
            // Already at max crop stage, transform to torchflower flower block
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

    @Override
    public void onRandomUpdate(Block block) {
        manager.callEvent(new CBlockRandomUpdateEvent(block));

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
                // At max crop stage, transform to torchflower flower block
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

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.TORCHFLOWER_SEEDS.createItemStack());
    }
}
