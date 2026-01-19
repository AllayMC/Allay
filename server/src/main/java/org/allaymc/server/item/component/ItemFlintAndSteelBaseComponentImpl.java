package org.allaymc.server.item.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.eventbus.event.block.BlockIgniteEvent;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.BlockFireBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class ItemFlintAndSteelBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemFlintAndSteelBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        super.useItemOnBlock(dimension, placeBlockPos, interactInfo);

        var player = interactInfo.player();
        if (player.getGameMode() == GameMode.ADVENTURE) {
            return false;
        }

        var clickedBlock = interactInfo.getClickedBlock();
        if (clickedBlock.getBlockType() == BlockTypes.TNT) {
            BlockTypes.TNT.getBlockBehavior().prime(new Block(clickedBlock.getBlockState(), new Position3i(interactInfo.clickedBlockPos(), dimension)));
            return true;
        }

        if (interactInfo.blockFace() == BlockFace.UP) {
            // Try to place fire on the ground
            var supportBlockState = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
            if (BlockFireBaseComponentImpl.canSupportFire(supportBlockState)) {
                var event = new BlockIgniteEvent(
                        new Block(supportBlockState, new Position3i(BlockFace.DOWN.offsetPos(placeBlockPos), dimension), 0),
                        null, player, BlockIgniteEvent.BlockIgniteCause.FLINT_AND_STEEL
                );
                if (event.call()) {
                    // Check if the block that the player clicked on is a soul fire converter
                    // In that case, we should place a soul fire instead of a normal fire
                    var fireBlockState = supportBlockState.getBlockType().hasBlockTag(BlockTags.SOUL_FIRE_CONVERTER) ? BlockTypes.SOUL_FIRE.getDefaultState() : BlockTypes.FIRE.getDefaultState();
                    dimension.setBlockState(placeBlockPos, fireBlockState);
                    dimension.addSound(MathUtils.center(placeBlockPos), SimpleSound.IGNITE);
                    if (player.getGameMode() != GameMode.CREATIVE) {
                        tryIncreaseDamage(1);
                    }
                    return true;
                }
            }
        } else {
            // Check if the block that the player clicked on can be ignited
            // This branch will be executed when the player clicks on the side of a block
            clickedBlock = interactInfo.getClickedBlock();
            if (clickedBlock.getBlockStateData().flameOdds() > 0) {
                var event = new BlockIgniteEvent(
                        new Block(clickedBlock.getBlockState(), new Position3i(interactInfo.clickedBlockPos(), dimension), 0),
                        null, player, BlockIgniteEvent.BlockIgniteCause.FLINT_AND_STEEL
                );
                if (event.call()) {
                    dimension.setBlockState(placeBlockPos, BlockTypes.FIRE.getDefaultState());
                    if (player.getGameMode() != GameMode.CREATIVE) {
                        tryIncreaseDamage(1);
                    }
                    return true;
                }
            }
        }

        // Return false to pass the call to BlockBehavior::onInteract() method since block types such as candle have flint_and_steel
        // related logic
        return false;
    }
}
