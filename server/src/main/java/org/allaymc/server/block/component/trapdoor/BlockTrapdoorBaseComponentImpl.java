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
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.Sound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.DIRECTION_4;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.OPEN_BIT;

/**
 * @author harry-xi
 */
public class BlockTrapdoorBaseComponentImpl extends BlockBaseComponentImpl {
    protected static final BiMap<BlockFace, Integer> TRAPDOOR_DIRECTION = HashBiMap.create(4);

    static {
        TRAPDOOR_DIRECTION.put(BlockFace.WEST, 0);
        TRAPDOOR_DIRECTION.put(BlockFace.EAST, 1);
        TRAPDOOR_DIRECTION.put(BlockFace.NORTH, 2);
        TRAPDOOR_DIRECTION.put(BlockFace.SOUTH, 3);
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
        blockState = blockState.setPropertyValue(DIRECTION_4, TRAPDOOR_DIRECTION.get(face));

        var blockFace = placementInfo.blockFace();
        if ((placementInfo.clickedPos().y() > 0.5 && blockFace != BlockFace.UP) || blockFace == BlockFace.DOWN) {
            blockState = blockState.setPropertyValue(BlockPropertyTypes.UPSIDE_DOWN_BIT, true);
        }

        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (interactInfo == null || interactInfo.player().isSneaking()) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        var isOpen = !blockState.getPropertyValue(OPEN_BIT);
        dimension.updateBlockProperty(OPEN_BIT, isOpen, pos);
        // Shouldn't use addLevelSoundEvent here, which has no effect on client for no reason
        dimension.addSound(MathUtils.center(pos), isOpen ? Sound.RANDOM_DOOR_OPEN : Sound.RANDOM_DOOR_CLOSE);
        return true;
    }
}
