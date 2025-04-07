package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class BlockCactusBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockCactusBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (!canGrowHere(dimension, placeBlockPos, true)) {
            return false;
        }

        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public boolean canCollideWithEntity() {
        return true;
    }

    @Override
    public void onCollideWithEntity(BlockStateWithPos current, Entity entity) {
        if (entity instanceof EntityDamageComponent damageComponent) {
            damageComponent.attack(DamageContainer.contact(0.5f));
        }
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (!canGrowHere(current.dimension(), current.pos(), true)) {
            current.dimension().breakBlock(current.pos(), null, null);
        }
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos current) {
        var dimension = current.dimension();
        var pos = current.pos();
        var block = current.blockState();
        var age = block.getPropertyValue(BlockPropertyTypes.AGE_16);
        if (age < 15) {
            block = block.withPropertyValue(BlockPropertyTypes.AGE_16, age + 1);
        } else if (age == 15) {
            block = block.withPropertyValue(BlockPropertyTypes.AGE_16, 0);
            if (canGrowHere(dimension, pos, false)) {
                for (var y = 1; y < 3; y++) {
                    var blockType = dimension.getBlockState(pos.x(), pos.y() + y, pos.z()).getBlockType();
                    if (blockType == BlockTypes.AIR) {
                        dimension.setBlockState(pos.x(), pos.y() + y, pos.z(), BlockTypes.CACTUS.getDefaultState());
                        break;
                    } else if (blockType != BlockTypes.CACTUS) {
                        break;
                    }
                }
            }
        }

        dimension.setBlockState(pos, block);
    }

    /**
     * Check if cactus can live/grow here.
     *
     * @param dimension the dimension that the cactus is in.
     * @param pos       the pos of the cactus.
     * @param recursive whether to check the block below recursively.
     *
     * @return {@code true} if cactus can live/grow here, {@code false} otherwise
     */
    protected boolean canGrowHere(Dimension dimension, Vector3ic pos, boolean recursive) {
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            if (dimension.getBlockState(face.offsetPos(pos)).getBlockType() != BlockTypes.AIR) {
                return false;
            }
        }

        var downBlockType = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType();
        if (recursive && downBlockType == BlockTypes.CACTUS) {
            return canGrowHere(dimension, BlockFace.DOWN.offsetPos(pos), true);
        }

        return downBlockType.hasBlockTag(BlockTags.SAND);
    }
}
