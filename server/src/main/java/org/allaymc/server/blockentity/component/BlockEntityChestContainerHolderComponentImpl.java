package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityPairableComponent;
import org.allaymc.api.container.impl.ChestContainer;
import org.allaymc.api.container.impl.DoubleChestContainer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.allaymc.server.component.annotation.Dependency;

/**
 * @author IWareQ
 */
public class  BlockEntityChestContainerHolderComponentImpl extends BlockEntityContainerHolderComponentImpl {
    @Getter
    protected final DoubleChestContainer doubleChestContainer;

    @Dependency
    protected BlockEntityPairableComponent pairableComponent;

    public BlockEntityChestContainerHolderComponentImpl() {
        super(ChestContainer::new);
        this.doubleChestContainer = new DoubleChestContainer();
    }

    @EventHandler
    @Override
    protected void onInteract(CBlockOnInteractEvent event) {
        var interactInfo = event.getInteractInfo();
        var player = interactInfo.player();
        if (player == null || player.isSneaking()) {
            return;
        }

        var pos = new Position3i(interactInfo.clickedBlockPos(), event.getDimension());
        if (!isSpaceAboveClear(pos)) {
            return;
        }

        if (pairableComponent.isPaired()) {
            var pair = pairableComponent.getPair();
            if (!isSpaceAboveClear((Position3i) pair.getPosition())) {
                return;
            }

            var left = getContainer();
            var right = ((BlockEntityContainerHolderComponent) pairableComponent.getPair()).getContainer();
            if (!pairableComponent.isLead()) {
                var temp = left;
                left = right;
                right = temp;
            }

            doubleChestContainer.setLeft(left);
            doubleChestContainer.setRight(right);
            doubleChestContainer.setBlockPos(new Position3i(interactInfo.clickedBlockPos(), event.getDimension()));
            doubleChestContainer.addViewer(player);
        } else {
            container.addViewer(player);
        }

        event.setSuccess(true);
    }

    protected boolean isSpaceAboveClear(Position3i pos) {
        return pos.dimension().getBlockState(BlockFace.UP.offsetPos(pos)).getBlockStateData().isTransparent();
    }
}
