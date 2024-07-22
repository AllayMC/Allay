package org.allaymc.server.block.component.button;

import static org.allaymc.api.data.VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.facing.BlockFacingDirectionBaseComponentImpl;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3ic;

import java.time.Duration;

@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "button_pressed_bit")
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "facing_direction")
public abstract class BlockButtonBaseComponentImpl extends BlockFacingDirectionBaseComponentImpl {
    public BlockButtonBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    abstract public Duration getActivationTime();

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        blockState = blockState.setProperty(BUTTON_PRESSED_BIT, false);
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        var pos = interactInfo.clickBlockPos();
        var blockState = dimension.getBlockState(pos);
        if (!blockState.getPropertyValue(BUTTON_PRESSED_BIT)) {
            blockState = blockState.setProperty(BUTTON_PRESSED_BIT, true);
            dimension.setBlockState(pos, blockState);
            dimension.getBlockUpdateService().scheduleBlockUpdate(pos, getActivationTime());
            dimension.addLevelSoundEvent(pos.x() + 0.5f, pos.y() + 0.5f, pos.z() + 0.5f, SoundEvent.BUTTON_CLICK_ON);
        }

        return super.onInteract(itemStack, dimension, interactInfo);
    }

    @Override
    public void onScheduledUpdate(BlockStateWithPos blockStateWithPos) {
        var pos = blockStateWithPos.pos();
        var blockState = blockStateWithPos.blockState();
        if (blockState.getPropertyValue(BUTTON_PRESSED_BIT)) {
            blockState = blockState.setProperty(BUTTON_PRESSED_BIT, false);
            pos.dimension().setBlockState(pos, blockState);
            pos.dimension().addLevelSoundEvent(pos.x() + 0.5f, pos.y() + 0.5f, pos.z() + 0.5f, SoundEvent.BUTTON_CLICK_OFF);
        }
    }
}
