package org.allaymc.server.item.component;

import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.component.BlockStrippableComponent;
import org.allaymc.api.block.data.OxidationLevel;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.ItemUseOnBlockSound;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3ic;

/**
 * @author IWareQ, daoge_cmd
 */
public class ItemAxeBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemAxeBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void rightClickItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        super.rightClickItemOnBlock(dimension, placeBlockPos, interactInfo);

        var clickedBlockPos = interactInfo.clickedBlockPos();
        var clickedBlockState = dimension.getBlockState(clickedBlockPos);
        if (!(clickedBlockState.getBehavior() instanceof BlockStrippableComponent strippableComponent)) {
            return;
        }

        if (strippableComponent.isStripped(clickedBlockState)) {
            return;
        }

        var strippedBlockState = strippableComponent.getStrippedBlockState(clickedBlockState);
        tryFadeBlock(dimension, interactInfo, strippedBlockState, () -> {
            // Idk why mojang does not use UsingItemOnBlock for player
            interactInfo.player().applyAction(SimpleEntityAction.SWING_ARM);
            dimension.addSound(clickedBlockPos.x(), clickedBlockPos.y(), clickedBlockPos.z(), new ItemUseOnBlockSound(clickedBlockState));
        });
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        super.useItemOnBlock(dimension, placeBlockPos, interactInfo);

        var clickedBlockState = interactInfo.getClickedBlock();
        if (!(clickedBlockState.getBehavior() instanceof BlockOxidationComponent oxidationComponent)) {
            return false;
        }

        var clickedBlockPos = interactInfo.clickedBlockPos();

        if (oxidationComponent.isWaxed()) {
            var nextBlockType = oxidationComponent.getBlockWithWaxed(false);
            tryFadeBlock(dimension, interactInfo, nextBlockType.copyPropertyValuesFrom(interactInfo.getClickedBlock().getBlockState()), () -> {
                dimension.addSound(clickedBlockPos, SimpleSound.WAX_REMOVED);
            });

            return true;
        }

        var oxidationLevel = oxidationComponent.getOxidationLevel();
        if (oxidationLevel == OxidationLevel.UNAFFECTED) {
            return false;
        }

        oxidationLevel = OxidationLevel.values()[oxidationLevel.ordinal() - 1];
        var nextBlockType = oxidationComponent.getBlockWithOxidationLevel(oxidationLevel);
        tryFadeBlock(dimension, interactInfo, nextBlockType.copyPropertyValuesFrom(interactInfo.getClickedBlock().getBlockState()), () -> {
            dimension.addSound(clickedBlockPos, SimpleSound.COPPER_SCRAPED);
        });

        return true;
    }

    private void tryFadeBlock(Dimension dimension, PlayerInteractInfo interactInfo, BlockState newBlockState, Runnable postBlockPlace) {
        var clickedBlockPos = interactInfo.clickedBlockPos();
        var oldBlock = new Block(
                interactInfo.getClickedBlock().getBlockState(),
                new Position3i(clickedBlockPos, dimension),
                0
        );

        var event = new BlockFadeEvent(oldBlock, newBlockState);
        if (event.call()) {
            dimension.setBlockState(clickedBlockPos, event.getNewBlockState());
            if (interactInfo.player().getGameMode() != GameMode.CREATIVE) {
                tryIncreaseDamage(1);
            }
            postBlockPlace.run();
        }
    }
}
