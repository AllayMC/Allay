package org.allaymc.server.block.component.crops;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.GROWTH;

/**
 * @author Dhaiven | daoge_cmd
 */
public abstract class BlockCropsBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockCropsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo.blockFace() != BlockFace.UP ||
            placementInfo.getClickedBlockState().getBlockType() != BlockTypes.FARMLAND) {
            return false;
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);

        if (face == BlockFace.DOWN && neighbor.getBlockType() != BlockTypes.FARMLAND) {
            current.breakBlock();
        }
    }

    /**
     * Calculate the chance the crop will grow during a random tick.
     *
     * @param block the block state to calculate the growth chance for.
     *
     * @return the chance the crop will grow.
     */
    protected float calculateGrowthChance(BlockStateWithPos block) {
        var points = 0.0f;

        var under = block.offsetPos(BlockFace.DOWN);
        for (var x = -1; x <= 1; x++) {
            for (var z = -1; z <= 1; z++) {
                var neighbor = under.offsetPos(x, 0, z);
                if (neighbor.getBlockType() == BlockTypes.FARMLAND) {
                    var farmlandPoints = 0f;
                    if (neighbor.getPropertyValue(BlockPropertyTypes.MOISTURIZED_AMOUNT) > 0) {
                        farmlandPoints = 4;
                    } else {
                        farmlandPoints = 2;
                    }
                    if (x != 0 || z != 0) {
                        farmlandPoints = (farmlandPoints - 1f) / 4f;
                    }
                    points += farmlandPoints;
                }
            }
        }

        var north = block.offsetPos(BlockFace.NORTH);
        var south = block.offsetPos(BlockFace.SOUTH);
        var west = block.offsetPos(BlockFace.WEST);
        var east = block.offsetPos(BlockFace.EAST);

        var northSouth = north.getBlockType() == this.blockType || south.getBlockType() == this.blockType;
        var westEast = west.getBlockType() == this.blockType || east.getBlockType() == this.blockType;
        if (northSouth && westEast) {
            points /= 2f;
        } else if (
                north.offsetPos(BlockFace.WEST).getBlockType() == this.blockType ||
                north.offsetPos(BlockFace.EAST).getBlockType() == this.blockType ||
                south.offsetPos(BlockFace.WEST).getBlockType() == this.blockType ||
                south.offsetPos(BlockFace.EAST).getBlockType() == this.blockType
        ) {
            points /= 2f;
        }

        return 1f / (25f / points + 1f);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        if (onBoneMealUsed(dimension, interactInfo.clickedBlockPos(), interactInfo.getClickedBlockState())) {
            interactInfo.player().tryConsumeItemInHand();
            dimension.addLevelEvent(MathUtils.center(interactInfo.clickedBlockPos()), LevelEvent.PARTICLE_CROP_GROWTH);
            return true;
        }

        return false;
    }

    protected boolean onBoneMealUsed(Dimension dimension, Vector3ic pos, BlockState crop) {
        var growth = crop.getPropertyValue(GROWTH);
        if (growth == GROWTH.getMax()) {
            return false;
        }

        var newCrop = crop.setPropertyValue(GROWTH, Math.min(growth + ThreadLocalRandom.current().nextInt(4) + 2, 7));
        var event = new BlockGrowEvent(new BlockStateWithPos(crop, new Position3i(pos, dimension)), newCrop);
        if (event.call()) {
            dimension.setBlockState(pos, event.getNewBlockState());
            return true;
        }

        return false;
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos current) {
        super.onRandomUpdate(current);

        if (current.getDimension().getLightService().getInternalLight(current.getPos()) < 8) {
            var event = new BlockFadeEvent(current, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                current.breakBlock();
            }
        } else {
            var growth = current.getPropertyValue(GROWTH);
            if (growth < GROWTH.getMax() && ThreadLocalRandom.current().nextFloat() <= calculateGrowthChance(current)) {
                var newCrop = current.setPropertyValue(GROWTH, growth + 1);
                var event = new BlockGrowEvent(current, newCrop);
                if (event.call()) {
                    current.getDimension().setBlockState(current.getPos(), event.getNewBlockState());
                }
            }
        }
    }
}
