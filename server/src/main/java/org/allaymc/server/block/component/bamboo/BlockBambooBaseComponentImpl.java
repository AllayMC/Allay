package org.allaymc.server.block.component.bamboo;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.BambooLeafSize;
import org.allaymc.api.block.property.enums.BambooStalkThickness;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * @author daoge_cmd
 */
public class BlockBambooBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockBambooBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null && placementInfo.blockFace() != BlockFace.UP) {
            return false;
        }

        var downState = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        var downType = downState.getBlockType();

        // If not on bamboo/sapling, delegate to sapling placement
        if (downType != BlockTypes.BAMBOO && downType != BlockTypes.BAMBOO_SAPLING) {
            if (!BlockBambooSaplingBaseComponentImpl.canSupportBamboo(dimension, placeBlockPos)) {
                return false;
            }
            // Place sapling instead
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                    BlockTypes.BAMBOO_SAPLING.getDefaultState(), placementInfo);
        }

        // On sapling: small leaves
        if (downType == BlockTypes.BAMBOO_SAPLING) {
            blockState = blockState
                    .setPropertyValue(BAMBOO_LEAF_SIZE, BambooLeafSize.SMALL_LEAVES)
                    .setPropertyValue(BAMBOO_STALK_THICKNESS, BambooStalkThickness.THIN);
        } else {
            // On bamboo: determine thickness and leaf size
            var thickness = determineThickness(dimension, placeBlockPos);
            var leafSize = determineLeafSize(dimension, placeBlockPos, thickness);
            blockState = blockState
                    .setPropertyValue(BAMBOO_STALK_THICKNESS, thickness)
                    .setPropertyValue(BAMBOO_LEAF_SIZE, leafSize);

            // Update leaves of bamboo below
            updateLeavesBelow(dimension, BlockFace.DOWN.offsetPos(placeBlockPos));

            // Cascade thickness to all bamboo below
            if (thickness == BambooStalkThickness.THICK) {
                updateThicknessBelow(dimension, BlockFace.DOWN.offsetPos(placeBlockPos));
            }

            // Check height for age bit
            int height = countBambooHeight(dimension, placeBlockPos);
            if (shouldStopGrowing(height)) {
                blockState = blockState.setPropertyValue(AGE_BIT, true);
            }
        }

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN) {
            var downType = neighbor.getBlockType();
            if (downType != BlockTypes.BAMBOO && downType != BlockTypes.BAMBOO_SAPLING &&
                !BlockBambooSaplingBaseComponentImpl.canSupportBamboo(block.getDimension(), block.getPosition())) {
                block.breakBlock();
            }
        }
    }

    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        super.onBreak(block, usedItem, entity);

        // Reset age bit of bamboo below
        var belowPos = BlockFace.DOWN.offsetPos(block.getPosition());
        var belowState = block.getDimension().getBlockState(belowPos);
        if (belowState.getBlockType() == BlockTypes.BAMBOO) {
            var newState = belowState.setPropertyValue(AGE_BIT, false);
            block.getDimension().setBlockState(belowPos, newState);
        }
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        if (block.getPropertyValue(AGE_BIT)) {
            return;
        }

        if (ThreadLocalRandom.current().nextInt(3) != 0) {
            return;
        }

        var abovePos = block.getPosition().add(0, 1, 0, new Vector3i());
        if (block.getDimension().getLightEngine().getInternalLight(abovePos) < 9) {
            return;
        }

        growFromTop(block.getDimension(), block.getPosition());
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        // Find topmost bamboo
        var pos = interactInfo.clickedBlockPos();
        var topPos = findTopBamboo(dimension, pos);
        int totalHeight = countTotalHeight(dimension, topPos);
        if (totalHeight >= 16) {
            return false;
        }

        if (growFromTop(dimension, topPos)) {
            interactInfo.player().tryConsumeItemInHand();
            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            return true;
        }

        return false;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.BAMBOO.createItemStack(1));
    }

    protected boolean growFromTop(Dimension dimension, Vector3ic pos) {
        // Find the topmost bamboo
        var topPos = findTopBamboo(dimension, pos);

        var abovePos = topPos.add(0, 1, 0, new Vector3i());
        if (dimension.getBlockState(abovePos).getBlockType() != BlockTypes.AIR) {
            return false;
        }

        int totalHeight = countTotalHeight(dimension, topPos);
        if (totalHeight >= 16) {
            return false;
        }

        var thickness = determineThickness(dimension, abovePos);
        var leafSize = determineLeafSize(dimension, abovePos, thickness);
        var newState = BlockTypes.BAMBOO.getDefaultState()
                .setPropertyValue(BAMBOO_STALK_THICKNESS, thickness)
                .setPropertyValue(BAMBOO_LEAF_SIZE, leafSize);

        if (shouldStopGrowing(totalHeight + 1)) {
            newState = newState.setPropertyValue(AGE_BIT, true);
        }

        var event = new BlockGrowEvent(
                new Block(dimension.getBlockState(topPos), new org.allaymc.api.math.position.Position3i(topPos, dimension)),
                newState
        );
        if (event.call()) {
            dimension.setBlockState(abovePos, event.getNewBlockState());
            updateLeavesBelow(dimension, topPos);
            // Cascade thickness to all bamboo below
            if (thickness == BambooStalkThickness.THICK) {
                updateThicknessBelow(dimension, topPos);
            }
            return true;
        }
        return false;
    }

    protected Vector3ic findTopBamboo(Dimension dimension, Vector3ic pos) {
        var current = new Vector3i(pos);
        while (true) {
            var above = new Vector3i(current.x, current.y + 1, current.z);
            if (dimension.getBlockState(above).getBlockType() != BlockTypes.BAMBOO) {
                return current;
            }
            current.set(above);
        }
    }

    protected int countTotalHeight(Dimension dimension, Vector3ic fromTop) {
        int count = 1;
        var current = new Vector3i(fromTop);
        // Count downward
        while (true) {
            current.y--;
            var type = dimension.getBlockState(current).getBlockType();
            if (type == BlockTypes.BAMBOO || type == BlockTypes.BAMBOO_SAPLING) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    protected int countBambooHeight(Dimension dimension, Vector3ic pos) {
        int count = 0;
        var current = new Vector3i(pos);
        // Count downward from below the given pos
        current.y--;
        while (true) {
            var type = dimension.getBlockState(current).getBlockType();
            if (type == BlockTypes.BAMBOO || type == BlockTypes.BAMBOO_SAPLING) {
                count++;
                current.y--;
            } else {
                break;
            }
        }
        return count + 1; // include the block at pos
    }

    protected boolean shouldStopGrowing(int height) {
        if (height >= 15) return true;
        if (height >= 11) return ThreadLocalRandom.current().nextInt(4) == 0;
        return false;
    }

    protected BambooStalkThickness determineThickness(Dimension dimension, Vector3ic pos) {
        // Thick if 3+ bamboo segments below
        int count = 0;
        var current = new Vector3i(pos);
        current.y--;
        while (count < 3) {
            var type = dimension.getBlockState(current).getBlockType();
            if (type == BlockTypes.BAMBOO || type == BlockTypes.BAMBOO_SAPLING) {
                count++;
                current.y--;
            } else {
                break;
            }
        }
        return count >= 3 ? BambooStalkThickness.THICK : BambooStalkThickness.THIN;
    }

    protected BambooLeafSize determineLeafSize(Dimension dimension, Vector3ic pos, BambooStalkThickness thickness) {
        if (thickness == BambooStalkThickness.THICK) {
            return BambooLeafSize.LARGE_LEAVES;
        }
        // For thin bamboo, check how many segments below
        int count = 0;
        var current = new Vector3i(pos);
        current.y--;
        while (count < 2) {
            var type = dimension.getBlockState(current).getBlockType();
            if (type == BlockTypes.BAMBOO || type == BlockTypes.BAMBOO_SAPLING) {
                count++;
                current.y--;
            } else {
                break;
            }
        }
        if (count >= 2) return BambooLeafSize.LARGE_LEAVES;
        if (count >= 1) return BambooLeafSize.SMALL_LEAVES;
        return BambooLeafSize.NO_LEAVES;
    }

    protected void updateLeavesBelow(Dimension dimension, Vector3ic pos) {
        var current = new Vector3i(pos);
        int distance = 1;
        while (distance <= 3) {
            var state = dimension.getBlockState(current);
            if (state.getBlockType() != BlockTypes.BAMBOO) break;

            BambooLeafSize newLeafSize;
            if (distance == 1) {
                newLeafSize = BambooLeafSize.LARGE_LEAVES;
            } else if (distance == 2) {
                newLeafSize = BambooLeafSize.SMALL_LEAVES;
            } else {
                newLeafSize = BambooLeafSize.NO_LEAVES;
            }

            if (state.getPropertyValue(BAMBOO_LEAF_SIZE) != newLeafSize) {
                var newState = state.setPropertyValue(BAMBOO_LEAF_SIZE, newLeafSize);
                dimension.setBlockState(current, newState);
            }

            current.y--;
            distance++;
        }
    }

    protected void updateThicknessBelow(Dimension dimension, Vector3ic pos) {
        var current = new Vector3i(pos);
        while (true) {
            var state = dimension.getBlockState(current);
            if (state.getBlockType() != BlockTypes.BAMBOO) break;

            if (state.getPropertyValue(BAMBOO_STALK_THICKNESS) != BambooStalkThickness.THICK) {
                var newState = state.setPropertyValue(BAMBOO_STALK_THICKNESS, BambooStalkThickness.THICK);
                dimension.setBlockState(current, newState);
            }

            current.y--;
        }
    }
}
