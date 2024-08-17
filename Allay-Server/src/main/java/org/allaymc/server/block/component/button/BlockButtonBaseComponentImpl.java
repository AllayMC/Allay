package org.allaymc.server.block.component.button;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.BlockStateWithPos;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.BlockFace;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3ic;

import lombok.Getter;

import java.time.Duration;

import static org.allaymc.api.data.VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT;
import static org.allaymc.api.data.VanillaBlockPropertyTypes.FACING_DIRECTION;

/**
 * Allay Project 2024/8/15
 *
 * @author Dhaiven
 */
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "facing_direction")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "button_pressed_bit")
public class BlockButtonBaseComponentImpl extends BlockBaseComponentImpl {
    @Getter
    protected final Duration activationTime;

    public BlockButtonBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Duration activationTime) {
        super(blockType);
        this.activationTime = activationTime;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        if (placementInfo != null) {
            blockState = blockState.setProperty(FACING_DIRECTION, placementInfo.blockFace().ordinal());
        }
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    @Override
    public boolean canKeepExisting(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        // Check if the neighbor is block below
        if (current.blockState().getPropertyValue(FACING_DIRECTION) != face.opposite().ordinal()) return true;
        return neighbor.blockState().getBlockType().getMaterial().isSolid();
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) return true;

        var pos = interactInfo.clickBlockPos();
        var blockState = dimension.getBlockState(pos);
        if (!blockState.getPropertyValue(BUTTON_PRESSED_BIT)) {
            updateBlockProperty(BUTTON_PRESSED_BIT, true, pos, dimension);
            dimension.getBlockUpdateService().scheduleBlockUpdate(pos, getActivationTime());
            dimension.addLevelSoundEvent(pos.x() + 0.5f, pos.y() + 0.5f, pos.z() + 0.5f, SoundEvent.BUTTON_CLICK_ON);
        }
    }

    @Override
    public void onScheduledUpdate(BlockStateWithPos blockStateWithPos) {
        var pos = blockStateWithPos.pos();
        var blockState = blockStateWithPos.blockState();
        if (blockState.getPropertyValue(BUTTON_PRESSED_BIT)) {
            updateBlockProperty(BUTTON_PRESSED_BIT, false, pos, pos.dimension());
            pos.dimension().addLevelSoundEvent(pos.x() + 0.5f, pos.y() + 0.5f, pos.z() + 0.5f, SoundEvent.BUTTON_CLICK_OFF);
        }
    }
}
