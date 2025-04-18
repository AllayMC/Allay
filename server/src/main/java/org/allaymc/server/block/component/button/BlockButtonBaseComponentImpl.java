package org.allaymc.server.block.component.button;

import lombok.Getter;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3ic;

import java.time.Duration;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.BUTTON_PRESSED_BIT;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;

/**
 * @author Dhaiven
 */
public class BlockButtonBaseComponentImpl extends BlockBaseComponentImpl {
    @Getter
    protected final Duration activationTime;

    public BlockButtonBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Duration activationTime) {
        super(blockType);
        this.activationTime = activationTime;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            blockState = blockState.setPropertyValue(FACING_DIRECTION, placementInfo.blockFace().ordinal());
        }
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);

        // Check if the neighbor is block below
        if (current.blockState().getPropertyValue(FACING_DIRECTION) != face.opposite().ordinal()) {
            return;
        }

        if (!neighbor.blockState().getBlockStateData().isSolid()) {
            current.pos().dimension().breakBlock(current.pos());
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var pos = interactInfo.clickedBlockPos();
        var clickedBlockState = interactInfo.getClickedBlockState();
        if (!clickedBlockState.getPropertyValue(BUTTON_PRESSED_BIT)) {
            dimension.updateBlockProperty(BUTTON_PRESSED_BIT, true, pos);
            dimension.getBlockUpdateService().scheduleBlockUpdateInDelay(pos, getActivationTime());
            dimension.addLevelSoundEvent(pos.x() + 0.5f, pos.y() + 0.5f, pos.z() + 0.5f, SoundEvent.BUTTON_CLICK_ON);
        }
        return true;
    }

    @Override
    public void onScheduledUpdate(BlockStateWithPos current) {
        var pos = current.pos();
        var blockState = current.blockState();
        if (blockState.getPropertyValue(BUTTON_PRESSED_BIT)) {
            pos.dimension().updateBlockProperty(BUTTON_PRESSED_BIT, false, pos);
            pos.dimension().addLevelSoundEvent(pos.x() + 0.5f, pos.y() + 0.5f, pos.z() + 0.5f, SoundEvent.BUTTON_CLICK_OFF);
        }
    }
}
