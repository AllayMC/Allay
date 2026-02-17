package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
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
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.GROWING_PLANT_AGE;

/**
 * @author daoge_cmd
 */
public class BlockCaveVinesBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final int MAX_AGE = 25;

    protected final boolean hasBerries;
    protected final boolean isHead;

    public BlockCaveVinesBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean hasBerries, boolean isHead) {
        super(blockType);
        this.hasBerries = hasBerries;
        this.isHead = isHead;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            if (placementInfo.blockFace() != BlockFace.DOWN) {
                return false;
            }
        }

        // Must hang from solid block or cave vines
        var aboveState = dimension.getBlockState(BlockFace.UP.offsetPos(placeBlockPos));
        if (!canBeSupportedBy(aboveState)) {
            return false;
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.UP) {
            if (!canBeSupportedBy(neighbor.getBlockState())) {
                block.breakBlock();
            }
        }
    }

    @Override
    public boolean canRandomUpdate() {
        return isHead;
    }

    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var random = ThreadLocalRandom.current();
        var age = block.getPropertyValue(GROWING_PLANT_AGE);
        var dimension = block.getDimension();

        // Maturation: 1/4 chance, age += 4
        if (random.nextInt(4) == 0 && age < MAX_AGE) {
            var newAge = Math.min(age + 4, MAX_AGE);
            var newState = block.getBlockState().setPropertyValue(GROWING_PLANT_AGE, newAge);
            dimension.setBlockState(block.getPosition(), newState);
        }

        // Downward growth: check below is air
        var belowPos = block.getPosition().add(0, -1, 0, new Vector3i());
        if (dimension.getBlockState(belowPos).getBlockType() != BlockTypes.AIR) {
            return;
        }

        // 1/10 chance for berry variant, else plain cave vines
        BlockState newVineState;
        if (random.nextInt(10) == 0) {
            newVineState = BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES.getDefaultState()
                    .setPropertyValue(GROWING_PLANT_AGE, 0);
        } else {
            newVineState = BlockTypes.CAVE_VINES.getDefaultState()
                    .setPropertyValue(GROWING_PLANT_AGE, 0);
        }

        var event = new BlockGrowEvent(block, newVineState);
        if (event.call()) {
            dimension.setBlockState(belowPos, event.getNewBlockState());
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var clickedBlock = interactInfo.getClickedBlock();

        // Bone meal on base cave vines: convert to berry variant at max age
        if (itemStack != null && itemStack.getItemType() == ItemTypes.BONE_MEAL) {
            if (!hasBerries) {
                var newState = BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES.getDefaultState()
                        .setPropertyValue(GROWING_PLANT_AGE, MAX_AGE);
                dimension.setBlockState(interactInfo.clickedBlockPos(), newState);
                interactInfo.player().tryConsumeItemInHand();
                dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
                return true;
            }
            return false;
        }

        // Berry interact: pick berries
        if (hasBerries) {
            var dropPos = MathUtils.center(interactInfo.clickedBlockPos());
            dimension.dropItem(ItemTypes.GLOW_BERRIES.createItemStack(1), dropPos);

            // Convert to base cave_vines with age 0
            var newState = BlockTypes.CAVE_VINES.getDefaultState()
                    .setPropertyValue(GROWING_PLANT_AGE, 0);
            dimension.setBlockState(interactInfo.clickedBlockPos(), newState);
            return true;
        }

        return false;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (hasBerries) {
            return Set.of(ItemTypes.GLOW_BERRIES.createItemStack(1));
        }
        return Set.of();
    }

    protected boolean canBeSupportedBy(BlockState blockState) {
        var type = blockState.getBlockType();
        if (isCaveVines(type)) {
            return true;
        }
        return blockState.getBlockStateData().isSolid();
    }

    protected boolean isCaveVines(BlockType<?> type) {
        return type == BlockTypes.CAVE_VINES ||
               type == BlockTypes.CAVE_VINES_BODY_WITH_BERRIES ||
               type == BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES;
    }
}
