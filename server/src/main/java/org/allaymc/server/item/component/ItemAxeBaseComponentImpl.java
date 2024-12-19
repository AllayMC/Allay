package org.allaymc.server.item.component;

import org.allaymc.api.block.component.BlockStrippableComponent;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.protocol.bedrock.data.GameType;
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
    public void rightClickItemOn(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        super.rightClickItemOn(dimension, placeBlockPos, interactInfo);

        var clickedBlockPos = interactInfo.clickedBlockPos();
        var clickedBlockState = dimension.getBlockState(clickedBlockPos);
        if (!(clickedBlockState.getBehavior() instanceof BlockStrippableComponent strippableComponent)) {
            return;
        }

        if (strippableComponent.isStripped(clickedBlockState)) {
            return;
        }

        var strippedBlockState = strippableComponent.getStrippedBlockState(clickedBlockState);
        dimension.setBlockState(clickedBlockPos, strippedBlockState);

        var oldBlockState = new BlockStateWithPos(
                clickedBlockState,
                new Position3i(interactInfo.clickedBlockPos(), dimension),
                0
        );
        var event = new BlockFadeEvent(oldBlockState, strippedBlockState);
        if (event.call()) {
            dimension.setBlockState(clickedBlockPos, event.getNewBlockState());
            if (interactInfo.player().getGameType() != GameType.CREATIVE) {
                tryReduceDurability(1);
            }

            dimension.addLevelSoundEvent(clickedBlockPos.x(), clickedBlockPos.y(), clickedBlockPos.z(), SoundEvent.ITEM_USE_ON, clickedBlockState.blockStateHash());
        }
    }
}
