package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.GROWTH;

/**
 * @author daoge_cmd
 */
public class BlockSweetBerryBushBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockSweetBerryBushBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null && placementInfo.blockFace() != BlockFace.UP) {
            return false;
        }
        if (!canSurvive(dimension, placeBlockPos)) {
            return false;
        }
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN && !canSurvive(block.getDimension(), block.getPosition())) {
            block.breakBlock();
        }
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var growth = block.getPropertyValue(GROWTH);
        if (growth >= 3) {
            return;
        }

        if (ThreadLocalRandom.current().nextInt(5) != 0) {
            return;
        }

        if (block.getDimension().getLightEngine().getInternalLight(block.getPosition()) < 9) {
            return;
        }

        var newState = block.getBlockState().setPropertyValue(GROWTH, growth + 1);
        var event = new BlockGrowEvent(block, newState);
        if (event.call()) {
            block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var clickedBlock = interactInfo.getClickedBlock();
        var growth = clickedBlock.getPropertyValue(GROWTH);

        // Bone meal: +1 growth (max 3)
        if (itemStack != null && itemStack.getItemType() == ItemTypes.BONE_MEAL) {
            if (growth < 3) {
                var newState = clickedBlock.getBlockState().setPropertyValue(GROWTH, growth + 1);
                var event = new BlockGrowEvent(clickedBlock, newState);
                if (event.call()) {
                    dimension.setBlockState(interactInfo.clickedBlockPos(), event.getNewBlockState());
                    interactInfo.player().tryConsumeItemInHand();
                    dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
                    return true;
                }
            }
            return false;
        }

        // Hand harvest: age >= 2 → drop berries, reset to age 1
        if (growth >= 2) {
            var random = ThreadLocalRandom.current();
            int dropCount = growth == 2 ? random.nextInt(1, 3) : random.nextInt(2, 4);
            var dropPos = MathUtils.center(interactInfo.clickedBlockPos());
            dimension.dropItem(ItemTypes.SWEET_BERRIES.createItemStack(dropCount), dropPos);

            var newState = clickedBlock.getBlockState().setPropertyValue(GROWTH, 1);
            dimension.setBlockState(interactInfo.clickedBlockPos(), newState);
            return true;
        }

        return false;
    }

    @Override
    public void onEntityInside(Block block, Entity entity) {
        var growth = block.getPropertyValue(GROWTH);
        if (growth <= 0) {
            return;
        }

        if (entity instanceof EntityLiving living) {
            if (ThreadLocalRandom.current().nextInt(20) == 0) {
                living.attack(DamageContainer.contact(0.5f));
            }
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var growth = block.getPropertyValue(GROWTH);
        var random = ThreadLocalRandom.current();
        int count;
        if (growth <= 1) {
            count = 1;
        } else if (growth == 2) {
            count = random.nextInt(1, 3);
        } else {
            count = random.nextInt(2, 4);
        }
        return Set.of(ItemTypes.SWEET_BERRIES.createItemStack(count));
    }

    protected boolean canSurvive(Dimension dimension, Vector3ic pos) {
        var downBlockType = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType();
        return downBlockType.hasBlockTag(BlockTags.DIRT) ||
               downBlockType == BlockTypes.DIRT_WITH_ROOTS ||
               downBlockType == BlockTypes.MOSS_BLOCK;
    }
}
