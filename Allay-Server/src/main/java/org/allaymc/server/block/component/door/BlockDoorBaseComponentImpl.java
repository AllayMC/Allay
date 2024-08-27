package org.allaymc.server.block.component.door;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.poi.BlockStateWithPos;
import org.allaymc.api.block.poi.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockTags;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.data.VanillaBlockPropertyTypes.*;

/**
 * Allay Project 2024/8/16
 *
 * @author Dhaiven
 */
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "direction")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "door_hinge_bit")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "open_bit")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "upper_block_bit")
public class BlockDoorBaseComponentImpl extends BlockBaseComponentImpl {
    protected final static BiMap<BlockFace, Integer> DOOR_DIRECTION = HashBiMap.create(4);

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
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        if (placeBlockPos.y() >= dimension.getDimensionInfo().maxHeight()) {
            return false;
        }

        if (placementInfo != null && placementInfo.blockFace() != BlockFace.UP) {
            return false;
        }

        var downBlockState = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() - 1, placeBlockPos.z());
        BlockType<?> blockType1 = downBlockState.getBlockType();
        if (!blockType1.getMaterial().isSolid()) {
            return false;
        }

        var upBlockState = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z());
        if (!upBlockState.getBlockType().hasBlockTag(VanillaBlockTags.REPLACEABLE)) {
            return false;
        }

        BlockFace face =  BlockFace.SOUTH;
        if (placementInfo != null) {
            face = placementInfo.player().getHorizontalFace();
        }
        blockState = blockState.setProperty(DIRECTION, DOOR_DIRECTION.get(face));

        var leftBlock = dimension.getBlockState(face.rotateYCCW().offsetPos(placeBlockPos)).getBlockType();
        var rightBlock = dimension.getBlockState(face.rotateY().offsetPos(placeBlockPos)).getBlockType();
        if (leftBlock == getBlockType() || (!rightBlock.getMaterial().isTransparent() && leftBlock.getMaterial().isTransparent())) { //Door hinge
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
            if (current.blockState().getPropertyValue(UPPER_BLOCK_BIT)) {
                return neighbor.blockState().getBlockType() == getBlockType();
            }

            return neighbor.blockState().getBlockType().getMaterial().isSolid();
        }

        return true;
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) return true;
        if (interactInfo == null) return false;

        Vector3i pos = (Vector3i) interactInfo.clickBlockPos();
        var blockState = dimension.getBlockState(pos);

        Vector3ic otherPos;
        if (blockState.getPropertyValue(UPPER_BLOCK_BIT)) {
            otherPos = pos.sub(0, 1, 0);
        } else {
            otherPos = pos.add(0, 1, 0);
        }

        var isOpen = !blockState.getPropertyValue(OPEN_BIT);

        updateBlockProperty(OPEN_BIT, isOpen, pos, dimension);
        updateBlockProperty(OPEN_BIT, isOpen, otherPos, dimension);

        dimension.addLevelSoundEvent(pos.x(), pos.y(), pos.z(), isOpen ? SoundEvent.DOOR_OPEN : SoundEvent.DOOR_CLOSE);
        return true;
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        return blockState.blockState().getPropertyValue(UPPER_BLOCK_BIT) ? Utils.EMPTY_ITEM_STACK_SET : super.getDrops(blockState, usedItem, entity);
    }
}
