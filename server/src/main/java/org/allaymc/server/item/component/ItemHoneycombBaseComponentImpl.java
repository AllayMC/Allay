package org.allaymc.server.item.component;

import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.joml.Vector3ic;

/**
 * @author IWareQ
 */
public class ItemHoneycombBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemHoneycombBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        super.useItemOnBlock(dimension, placeBlockPos, interactInfo);

        var clickedBlockState = interactInfo.getClickedBlockState();
        if (!(clickedBlockState.getBehavior() instanceof BlockOxidationComponent oxidationComponent)) {
            return false;
        }

        var waxed = oxidationComponent.isWaxed();
        if (waxed) {
            return false;
        }

        var clickedBlockPos = interactInfo.clickedBlockPos();
        var nextBlockType = oxidationComponent.getBlockWithWaxed(true);
        var oldBlockState = new BlockStateWithPos(
                dimension.getBlockState(clickedBlockPos),
                new Position3i(clickedBlockPos, dimension),
                0
        );
        var blockFadeEvent = new BlockFadeEvent(oldBlockState, nextBlockType.copyPropertyValuesFrom(oldBlockState.blockState()));
        if (blockFadeEvent.call()) {
            dimension.setBlockState(clickedBlockPos, blockFadeEvent.getNewBlockState());
            interactInfo.player().tryConsumeItemInHand();
            dimension.addLevelEvent(clickedBlockPos, LevelEvent.PARTICLE_WAX_ON);
            return true;
        }

        return false;
    }
}
