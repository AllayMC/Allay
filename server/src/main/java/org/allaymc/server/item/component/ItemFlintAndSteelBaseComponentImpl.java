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
import org.allaymc.server.block.component.fire.BlockFireBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * {@code ItemFlintAndSteelBaseComponentImpl} primes TNT and creates fire during
 * block interactions. Ground ignition selects normal or soul fire from the
 * support block, while side ignition requires a flammable clicked block.
 * Unhandled uses continue into block-specific interaction, including candle
 * behavior.
 *
 * @author daoge_cmd
 */
public class ItemFlintAndSteelBaseComponentImpl extends ItemBaseComponentImpl {
    /**
     * Creates a flint and steel component from the supplied stack initialization data.
     */
    public ItemFlintAndSteelBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Primes TNT or attempts a cancellable fire placement for a player interaction.
     * The method dispatches inherited component use but continues regardless of
     * its result, rejects adventure mode, and requires a non-{@code null}
     * {@code interactInfo}. TNT priming returns {@code true} without requesting
     * item damage. Accepted fire placement requests one damage point outside
     * creative mode; cancelled or unsupported ignition returns {@code false}
     * so block-specific interaction can continue.
     */
    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        super.useItemOnBlock(dimension, placeBlockPos, interactInfo);

        var player = interactInfo.player();
        if (player.getGameMode() == GameMode.ADVENTURE) {
            return false;
        }

        var clickedBlock = interactInfo.getClickedBlock();
        if (clickedBlock.getBlockType() == BlockTypes.TNT) {
            BlockTypes.TNT.getBlockBehavior().prime(new Position3i(interactInfo.clickedBlockPos(), dimension));
            return true;
        }

        if (interactInfo.blockFace() == BlockFace.UP) {
            var supportBlockState = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
            if (BlockFireBaseComponentImpl.canSupportFire(supportBlockState)) {
                var event = new BlockIgniteEvent(
                        new Block(supportBlockState, new Position3i(BlockFace.DOWN.offsetPos(placeBlockPos), dimension), 0),
                        null, player, BlockIgniteEvent.BlockIgniteCause.FLINT_AND_STEEL
                );
                if (event.call()) {
                    // The supporting block selects soul fire; the ignite event refers
                    // to that support even though the new fire occupies placeBlockPos.
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

        // Let block-specific interactions handle uses such as candle ignition when
        // this component has neither primed TNT nor accepted a fire placement.
        return false;
    }
}
