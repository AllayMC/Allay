package org.allaymc.server.block.component.trapdoor;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.DIRECTION_4;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.OPEN_BIT;

public class BlockTrapdoorBaseComponentImpl extends BlockBaseComponentImpl {
    protected static final BiMap<BlockFace, Integer> TRAPDOOR_DIRECTION = HashBiMap.create(4);

    static {
        TRAPDOOR_DIRECTION.put(BlockFace.EAST, 1);
        TRAPDOOR_DIRECTION.put(BlockFace.SOUTH, 3);
        TRAPDOOR_DIRECTION.put(BlockFace.WEST, 0);
        TRAPDOOR_DIRECTION.put(BlockFace.NORTH, 2);
    }

    public BlockTrapdoorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }

        var face = placementInfo.player().getHorizontalFace();
        blockState = blockState.setProperty(DIRECTION_4, TRAPDOOR_DIRECTION.get(face));

        var blockFace = placementInfo.blockFace();
        if ((placementInfo.clickedPos().y() > 0.5 && blockFace != BlockFace.UP) || blockFace == BlockFace.DOWN) {
            blockState = blockState.setProperty(BlockPropertyTypes.UPSIDE_DOWN_BIT, true);
        }

        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) return true;
        if (interactInfo == null) return false;
        Vector3i pos = (Vector3i) interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        var isOpen = !blockState.getPropertyValue(OPEN_BIT);
        dimension.updateBlockProperty(OPEN_BIT, isOpen, pos);
        dimension.addLevelSoundEvent(pos.x(), pos.y(), pos.z(), isOpen ? SoundEvent.DOOR_OPEN : SoundEvent.DOOR_CLOSE);
        return true;
    }
}
