package org.allaymc.server.item.component;

import org.allaymc.api.block.component.BlockWoodBaseComponent;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3ic;

/**
 * @author IWareQ, daoge_cmd
 */
public class ItemAxeBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemAxeBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        super.useItemOnBlock(dimension, placeBlockPos, interactInfo);

        var clickedBlockPos = interactInfo.clickedBlockPos();
        var clickedBlockState = dimension.getBlockState(clickedBlockPos);
        if (!(clickedBlockState.getBehavior() instanceof BlockWoodBaseComponent woodBaseComponent)) {
            return false;
        }

        if (woodBaseComponent.isStripped(clickedBlockState)) {
            return false;
        }

        var strippedBlockState = woodBaseComponent.getStrippedBlockState(clickedBlockState);
        dimension.setBlockState(clickedBlockPos, strippedBlockState);

        var player = interactInfo.player();
        player.getItemInHand().tryReduceDurability(1);
        player.swingArm();
        dimension.addLevelSoundEvent(
                clickedBlockPos.x() + 0.5f, clickedBlockPos.y() + 0.5f, clickedBlockPos.z() + 0.5f,
                SoundEvent.ITEM_USE_ON, clickedBlockState.blockStateHash()
        );
        return true;
    }
}
