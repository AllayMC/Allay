package org.allaymc.server.block.component.redstone.button;

import static org.allaymc.api.data.VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.facing.BlockFacingBaseComponentImpl;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

import java.time.Duration;

@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "button_pressed_bit")
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "facing_direction")
public abstract class BlockButtonBaseComponentImpl extends BlockFacingBaseComponentImpl {
    public BlockButtonBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    abstract public Duration getActivationTime();

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (!super.onInteract(itemStack, dimension, interactInfo)) return false;

        var pos = interactInfo.clickBlockPos();
        var blockState = dimension.getBlockState(pos);
        if (!blockState.getPropertyValue(BUTTON_PRESSED_BIT)) {
            blockState.setProperty(BUTTON_PRESSED_BIT, true);
            dimension.getBlockUpdateService().scheduleBlockUpdate(pos, getActivationTime());
            dimension.addLevelSoundEvent(pos.x() + 0.5f, pos.y() + 0.5f, pos.z() + 0.5f, SoundEvent.BUTTON_CLICK_ON);
        }

        return true;
    }

    @Override
    public void onScheduledUpdate(BlockStateWithPos blockState) {
        var pos = blockState.pos();
        if (blockState.blockState().getPropertyValue(BUTTON_PRESSED_BIT)) {
            blockState.blockState().setProperty(BUTTON_PRESSED_BIT, false);
            pos.dimension().addLevelSoundEvent(pos.x() + 0.5f, pos.y() + 0.5f, pos.z() + 0.5f, SoundEvent.BUTTON_CLICK_OFF);
        }
    }
}
