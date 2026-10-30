package org.allaymc.server.block.component.container;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityDropper;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3ic;

import java.time.Duration;

/**
 * {@code BlockDropperBaseComponentImpl} orients dropper placement and converts
 * redstone rising edges into delayed block-entity dispense attempts. Placement
 * uses the clicked surface when no player exists, otherwise nearby eye height can
 * select a vertical output before horizontal facing is considered.
 *
 * <p>
 * Neighbor updates compare current power with {@code TRIGGERED_BIT}. A rising edge
 * sets that bit and schedules the delayed callback; a falling edge clears the bit
 * while the callback remains queued. The callback uses the block entity at
 * execution time and does not require power to remain high. Comparator queries
 * delegate inventory fullness to the resolved block entity.
 *
 * @author daoge_cmd
 */
public class BlockDropperBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code DISPENSE_DELAY} postpones a rising-edge attempt by two hundred
     * milliseconds. The block-update manager converts this duration into its
     * tick-based scheduling delay.
     */
    protected static final Duration DISPENSE_DELAY = Duration.ofMillis(200);

    /**
     * {@code blockEntityHolderComponent} resolves dropper dispensing and inventory
     * state. Component initialization injects this dependency before the composed
     * block behavior handles its world callbacks.
     */
    @Dependency
    protected BlockBlockEntityHolderComponent<BlockEntityDropper> blockEntityHolderComponent;

    /**
     * Creates this container block component for the supplied registered block
     * type.
     */
    public BlockDropperBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places the dropper with a facing derived from the player or clicked surface.
     * A {@code null} placement context writes the supplied state directly; other
     * placements process block properties before assigning the facing.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var facing = calculateFacing(placementInfo);
        var processedState = processBlockProperties(blockState, placeBlockPos, placementInfo)
                .setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, facing.ordinal());

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), processedState, placementInfo);
    }

    /**
     * Returns a vertical or horizontal output direction for the placement. A
     * context without a player uses the opposite clicked face. For a player within
     * two blocks on both horizontal axes, an eye position more than two blocks
     * above the clicked block selects upward, and an eye below its base selects
     * downward. Other positions use the opposite horizontal facing, so proximity
     * and eye height jointly control vertical placement.
     */
    protected BlockFace calculateFacing(PlayerInteractInfo placementInfo) {
        var player = placementInfo.player();
        if (player == null) {
            return placementInfo.blockFace().opposite();
        }

        var clickedPos = placementInfo.clickedBlockPos();
        var dx = Math.abs(player.getLocation().x() - clickedPos.x());
        var dz = Math.abs(player.getLocation().z() - clickedPos.z());

        // Only nearby players select vertical outputs from eye height; farther
        // placements retain the opposite horizontal facing even when the player is
        // high above.
        if (dx < 2 && dz < 2) {
            var eyeY = player.getLocation().y() + player.getEyeHeight();
            var blockY = clickedPos.y();

            if (eyeY - blockY > 2) {
                return BlockFace.UP;
            } else if (blockY - eyeY > 0) {
                return BlockFace.DOWN;
            }
        }

        return player.getHorizontalFace().opposite();
    }

    /**
     * Runs shared neighbor handling and schedules one dispense attempt on a
     * detected redstone rising edge. {@code TRIGGERED_BIT} stores the latest
     * powered state so sustained power does not schedule again. The bit is raised
     * before the delay is requested, and a falling edge clears it without
     * cancelling the queued attempt. The later callback invokes dispensing without
     * rechecking power, so removing power during the delay still permits that
     * attempt.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var isPowered = block.isPowered();
        var isTriggered = block.getPropertyValue(BlockPropertyTypes.TRIGGERED_BIT);

        // Publish the triggered bit before scheduling so later neighbor updates
        // observe the edge as handled while its delayed dispense callback is still
        // pending.
        if (isPowered && !isTriggered) {
            block.updateBlockProperty(BlockPropertyTypes.TRIGGERED_BIT, true);
            block.scheduleUpdateInDelay(DISPENSE_DELAY);
        } else if (!isPowered && isTriggered) {
            block.updateBlockProperty(BlockPropertyTypes.TRIGGERED_BIT, false);
        }
    }

    /**
     * Attempts to dispense through the resolved block entity after the scheduled
     * delay. The callback does not recheck current redstone power; a missing
     * dropper entity leaves the attempt without an action.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        var blockEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (blockEntity != null) {
            blockEntity.tryDispense();
        }
    }

    /**
     * Returns whether this container supplies its own comparator signal.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns the resolved dropper inventory's comparator fullness signal. A
     * missing block entity returns zero; otherwise the inventory calculates the
     * signal from its current item stacks.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        var blockEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (blockEntity == null) {
            return 0;
        }
        return blockEntity.getContainer().calculateComparatorSignal();
    }
}
