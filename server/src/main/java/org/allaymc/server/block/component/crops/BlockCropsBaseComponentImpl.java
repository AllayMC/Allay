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

        if (face == BlockFace.DOWN && neighbor.blockState().getBlockType() != BlockTypes.FARMLAND) {
            current.pos().dimension().breakBlock(current.pos());
        }
    }

    /**
     * Calculate the chance the crop will grow during a random tick.
     *
     * @param blockStateWithPos the block state to calculate the growth chance for.
     *
     * @return the chance the crop will grow.
     */
    protected float calculateGrowthChance(BlockStateWithPos blockStateWithPos) {
        var points = 0.0f;

        var dimension = blockStateWithPos.dimension();
        var block = blockStateWithPos.blockState();
        var under = BlockFace.DOWN.offsetPos(blockStateWithPos.pos());

        for (var x = -1; x <= 1; x++) {
            for (var z = -1; z <= 1; z++) {
                block = dimension.getBlockState(under.x() + x, under.y(), under.z() + z);
                if (block.getBlockType() == BlockTypes.FARMLAND) {
                    var farmlandPoints = 0.0f;
                    if (block.getPropertyValue(BlockPropertyTypes.MOISTURIZED_AMOUNT) > 0) {
                        farmlandPoints = 4;
                    } else {
                        farmlandPoints = 2;
                    }
                    if (x != 0 || z != 0) {
                        farmlandPoints = (farmlandPoints - 1.0f) / 4.0f;
                    }
                    points += farmlandPoints;
                }
            }
        }

        var north = BlockFace.NORTH.offsetPos(blockStateWithPos.pos());
        var south = BlockFace.SOUTH.offsetPos(blockStateWithPos.pos());

        var northSouth = dimension.getBlockState(north).getBlockType() == this.blockType ||
                         dimension.getBlockState(south).getBlockType() == this.blockType;
        var westEast = dimension.getBlockState(BlockFace.WEST.offsetPos(blockStateWithPos.pos())).getBlockType() == this.blockType ||
                       dimension.getBlockState(BlockFace.EAST.offsetPos(blockStateWithPos.pos())).getBlockType() == this.blockType;
        if (northSouth && westEast) {
            points /= 2.0f;
        } else if (dimension.getBlockState(BlockFace.WEST.offsetPos(north)) == this.blockType ||
                   dimension.getBlockState(BlockFace.EAST.offsetPos(north)) == this.blockType ||
                   dimension.getBlockState(BlockFace.WEST.offsetPos(south)) == this.blockType ||
                   dimension.getBlockState(BlockFace.EAST.offsetPos(south)) == this.blockType) {
            points /= 2.0f;
        }

        return 1.0f / (25.0f / points + 1.0f);
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
        var growth = crop.getPropertyValue(BlockPropertyTypes.GROWTH);
        if (growth == 7) {
            return false;
        }

        var newCrop = crop.setPropertyValue(BlockPropertyTypes.GROWTH, Math.min(growth + ThreadLocalRandom.current().nextInt(4) + 2, 7));
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

        if (current.dimension().getLightService().getInternalLight(current.pos()) < 8) {
            var event = new BlockFadeEvent(current, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                current.dimension().breakBlock(current.pos(), null, null);
            }
        } else {
            var growth = current.blockState().getPropertyValue(BlockPropertyTypes.GROWTH);
            if (growth < 7 && ThreadLocalRandom.current().nextFloat() <= calculateGrowthChance(current)) {
                var newCrop = current.blockState().setPropertyValue(BlockPropertyTypes.GROWTH, growth + 1);
                var event = new BlockGrowEvent(current, newCrop);
                if (event.call()) {
                    current.dimension().setBlockState(current.pos(), event.getNewBlockState());
                }
            }
        }
    }
}
