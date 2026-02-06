package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.dto.NeighborUpdateContext;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityDispenser;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3ic;

import java.time.Duration;

/**
 * @author daoge_cmd
 */
public class BlockDispenserBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final Duration DISPENSE_DELAY = Duration.ofMillis(200);

    @Dependency
    protected BlockBlockEntityHolderComponent<BlockEntityDispenser> blockEntityHolderComponent;

    public BlockDispenserBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

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

    protected BlockFace calculateFacing(PlayerInteractInfo placementInfo) {
        var player = placementInfo.player();
        if (player == null) {
            return placementInfo.blockFace().opposite();
        }

        var clickedPos = placementInfo.clickedBlockPos();
        var dx = Math.abs(player.getLocation().x() - clickedPos.x());
        var dz = Math.abs(player.getLocation().z() - clickedPos.z());

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

    @Override
    public void onNeighborUpdate(NeighborUpdateContext context) {
        super.onNeighborUpdate(context);
        var block = context.block();

        var isPowered = block.isPowered();
        var isTriggered = block.getPropertyValue(BlockPropertyTypes.TRIGGERED_BIT);

        if (isPowered && !isTriggered) {
            block.updateBlockProperty(BlockPropertyTypes.TRIGGERED_BIT, true);
            block.scheduleUpdateInDelay(DISPENSE_DELAY);
        } else if (!isPowered && isTriggered) {
            block.updateBlockProperty(BlockPropertyTypes.TRIGGERED_BIT, false);
        }
    }

    @Override
    public void onScheduledUpdate(Block block) {
        blockEntityHolderComponent.getBlockEntity(block.getPosition()).tryDispense();
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        return blockEntityHolderComponent.getBlockEntity(block.getPosition()).getContainer().calculateComparatorSignal();
    }
}
