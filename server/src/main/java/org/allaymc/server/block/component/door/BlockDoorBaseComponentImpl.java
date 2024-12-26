package org.allaymc.server.block.component.door;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * @author Dhaiven
 */
public class BlockDoorBaseComponentImpl extends BlockBaseComponentImpl {
    protected static final BiMap<BlockFace, Integer> DOOR_DIRECTION = HashBiMap.create(4);

    static {
        DOOR_DIRECTION.put(BlockFace.EAST, 0);
        DOOR_DIRECTION.put(BlockFace.SOUTH, 1);
        DOOR_DIRECTION.put(BlockFace.WEST, 2);
        DOOR_DIRECTION.put(BlockFace.NORTH, 3);
    }

    public BlockDoorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placeBlockPos.y() >= dimension.getDimensionInfo().maxHeight()) {
            return false;
        }

        if (placementInfo != null && placementInfo.blockFace() != BlockFace.UP) {
            return false;
        }

        var downBlockState = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() - 1, placeBlockPos.z());
        if (!downBlockState.getBlockStateData().isSolid()) {
            return false;
        }

        var upBlockState = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z());
        if (!upBlockState.getBlockType().hasBlockTag(BlockCustomTags.REPLACEABLE)) {
            return false;
        }

        BlockFace face = BlockFace.SOUTH;
        if (placementInfo != null) {
            face = placementInfo.player().getHorizontalFace();
        }
        blockState = blockState.setProperty(DIRECTION_4, DOOR_DIRECTION.get(face));

        var leftBlockState = dimension.getBlockState(face.rotateYCCW().offsetPos(placeBlockPos));
        var rightBlockState = dimension.getBlockState(face.rotateY().offsetPos(placeBlockPos));

        var hingeOnLeft = leftBlockState.getBlockType() == getBlockType() ||
                          (!rightBlockState.getBlockStateData().isTransparent() && leftBlockState.getBlockStateData().isTransparent());
        if (hingeOnLeft) { // Door hinge
            blockState = blockState.setProperty(DOOR_HINGE_BIT, true);
        }

        dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                blockState.setProperty(UPPER_BLOCK_BIT, false),
                placementInfo
        ); //Bottom

        dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z(),
                blockState.setProperty(UPPER_BLOCK_BIT, true),
                placementInfo
        ); //Top

        return true;
    }

    @Override
    public boolean canKeepExisting(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (face == BlockFace.UP) {
            return current.blockState().getPropertyValue(UPPER_BLOCK_BIT) || neighbor.blockState().getBlockType() == getBlockType();
        } else if (face == BlockFace.DOWN) {
            return current.blockState().getPropertyValue(UPPER_BLOCK_BIT)
                    ? neighbor.blockState().getBlockType() == getBlockType()
                    : neighbor.blockState().getBlockStateData().isSolid();
        }

        return true;
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) return true;
        if (interactInfo == null) return false;

        Vector3i pos = (Vector3i) interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);

        Vector3ic otherPos = blockState.getPropertyValue(UPPER_BLOCK_BIT)
                ? pos.sub(0, 1, 0)
                : pos.add(0, 1, 0);

        var isOpen = !blockState.getPropertyValue(OPEN_BIT);

        dimension.updateBlockProperty(OPEN_BIT, isOpen, pos);
        dimension.updateBlockProperty(OPEN_BIT, isOpen, otherPos);

        dimension.addLevelSoundEvent(pos.x(), pos.y(), pos.z(), isOpen ? SoundEvent.DOOR_OPEN : SoundEvent.DOOR_CLOSE);
        return true;
    }

    @Override
    public void onBreak(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        if (blockState.blockState().getPropertyValue(UPPER_BLOCK_BIT)) {
            blockState.pos().dimension().breakBlock(((Vector3i) blockState.pos()).sub(0, 1, 0), null, entity);
        }
        super.onBreak(blockState, usedItem, entity);
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        return blockState.blockState().getPropertyValue(UPPER_BLOCK_BIT) ? Utils.EMPTY_ITEM_STACK_SET : super.getDrops(blockState, usedItem, entity);
    }
}
