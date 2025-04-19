package org.allaymc.server.block.component.trapdoor;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.BlockPlaceHelper;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.Sound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.OPEN_BIT;

/**
 * @author harry-xi
 */
public class BlockTrapdoorBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockTrapdoorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }

        blockState = BlockPlaceHelper.processDirection4Property(blockState, placeBlockPos, placementInfo);
        blockState = BlockPlaceHelper.processUpsideDownBitProperty(blockState, placeBlockPos, placementInfo);
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

        var clickedBlockState = interactInfo.getClickedBlockState();
        var isOpen = !clickedBlockState.getPropertyValue(OPEN_BIT);
        clickedBlockState.updateBlockProperty(OPEN_BIT, isOpen);
        // Shouldn't use addLevelSoundEvent here, which has no effect on client for no reason
        dimension.addSound(MathUtils.center(clickedBlockState.getPos()), isOpen ? Sound.RANDOM_DOOR_OPEN : Sound.RANDOM_DOOR_CLOSE);
        return true;
    }
}
