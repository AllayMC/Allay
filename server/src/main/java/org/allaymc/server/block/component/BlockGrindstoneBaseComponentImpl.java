package org.allaymc.server.block.component;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.Attachment;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.impl.GrindstoneContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.ATTACHMENT;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.DIRECTION_4;

/**
 * @author IWareQ
 */
public class BlockGrindstoneBaseComponentImpl extends BlockBaseComponentImpl {
    protected static final BiMap<BlockFace, Integer> DIRECTION = HashBiMap.create(4);

    static {
        DIRECTION.put(BlockFace.SOUTH, 0);
        DIRECTION.put(BlockFace.WEST, 1);
        DIRECTION.put(BlockFace.NORTH, 2);
        DIRECTION.put(BlockFace.EAST, 3);
    }

    public BlockGrindstoneBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }

        var playerFace = placementInfo.player().getHorizontalFace();
        var clickedFace = placementInfo.blockFace();

        if (clickedFace == BlockFace.DOWN) {
            blockState = blockState.setPropertyValue(ATTACHMENT, Attachment.HANGING);
            blockState = blockState.setPropertyValue(DIRECTION_4, DIRECTION.get(playerFace.opposite()));
        } else if (clickedFace == BlockFace.UP) {
            blockState = blockState.setPropertyValue(ATTACHMENT, Attachment.STANDING);
            blockState = blockState.setPropertyValue(DIRECTION_4, DIRECTION.get(playerFace));
        } else {
            blockState = blockState.setPropertyValue(ATTACHMENT, Attachment.SIDE);
            blockState = blockState.setPropertyValue(DIRECTION_4, DIRECTION.get(clickedFace));
        }

        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var player = interactInfo.player();
        if (player.isSneaking()) {
            return false;
        }

        var grindstoneContainer = new GrindstoneContainer();
        grindstoneContainer.setBlockPos(new Position3i(interactInfo.clickedBlockPos(), interactInfo.player().getDimension()));
        grindstoneContainer.addViewer(player);
        return true;
    }
}
