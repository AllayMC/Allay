package org.allaymc.server.item.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.eventbus.event.block.BlockIgniteEvent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockFireBaseComponentImpl;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
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

        if (player.getGameType() == GameType.ADVENTURE) {
            return false;
        }

        if (player.getGameType() != GameType.CREATIVE) {
            // The durability will always be reduced
            // no matter if the fire is spawned successfully
            tryIncreaseDamage(1);
        }

        var clickedBlock = interactInfo.getClickedBlockState();
        if (clickedBlock.getBlockType() == BlockTypes.TNT) {
            BlockTypes.TNT.getBlockBehavior().prime(new BlockStateWithPos(clickedBlock, new Position3i(interactInfo.clickedBlockPos(), dimension)));
            return true;
        }

        if (interactInfo.blockFace() == BlockFace.UP) {
            // Try to place fire on the ground
            var supportBlockState = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
            if (BlockFireBaseComponentImpl.canSupportFire(supportBlockState)) {
                var event = new BlockIgniteEvent(
                        new BlockStateWithPos(supportBlockState, new Position3i(BlockFace.DOWN.offsetPos(placeBlockPos), dimension), 0),
                        null, player, BlockIgniteEvent.BlockIgniteCause.FLINT_AND_STEEL
                );
                if (event.call()) {
                    // Check if the block that the player clicked on is a soul fire converter
                    // In that case, we should place a soul fire instead of a normal fire
                    var fireBlockState = supportBlockState.getBlockType().hasBlockTag(BlockCustomTags.SOUL_FIRE_CONVERTER) ? BlockTypes.SOUL_FIRE.getDefaultState() : BlockTypes.FIRE.getDefaultState();
                    dimension.setBlockState(placeBlockPos, fireBlockState);
                    dimension.addLevelSoundEvent(MathUtils.center(placeBlockPos), SoundEvent.IGNITE);
                }
            }
        } else {
            // Check if the block that the player clicked on can be ignited
            // This branch will be executed when the player clicks on the side of a block
            var clickedBlockState = interactInfo.getClickedBlockState();
            if (clickedBlockState.getBlockStateData().flameOdds() > 0) {
                var event = new BlockIgniteEvent(
                        new BlockStateWithPos(clickedBlockState, new Position3i(interactInfo.clickedBlockPos(), dimension), 0),
                        null, player, BlockIgniteEvent.BlockIgniteCause.FLINT_AND_STEEL
                );
                if (event.call()) {
                    dimension.setBlockState(placeBlockPos, BlockTypes.FIRE.getDefaultState());
                }
            }
        }

        return true;
    }
}
