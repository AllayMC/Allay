package org.allaymc.server.item.component;

import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
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

        var clickedBlockState = interactInfo.getClickedBlock();
        if (!(clickedBlockState.getBehavior() instanceof BlockOxidationComponent oxidationComponent)) {
            return false;
        }

        if (oxidationComponent.isWaxed()) {
            return false;
        }

        var clickedBlockPos = interactInfo.clickedBlockPos();
        var nextBlockType = oxidationComponent.getBlockWithWaxed(true);
        var oldBlock = new Block(dimension.getBlockState(clickedBlockPos), new Position3i(clickedBlockPos, dimension), 0);
        dimension.setBlockState(clickedBlockPos, nextBlockType.copyPropertyValuesFrom(oldBlock.getBlockState()));
        interactInfo.player().tryConsumeItemInHand();
        dimension.addSound(clickedBlockPos, SimpleSound.SIGN_WAXED);
        return true;
    }
}
