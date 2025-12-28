package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityPairableComponent;
import org.allaymc.api.container.interfaces.BlockContainer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.container.impl.ChestContainerImpl;
import org.allaymc.server.container.impl.DoubleChestContainerImpl;

/**
 * @author IWareQ | daoge_cmd
 */
public class BlockEntityChestContainerHolderComponentImpl extends BlockEntityContainerHolderComponentImpl {
    @Getter
    protected final DoubleChestContainerImpl doubleChestContainer;

    @Dependency
    protected BlockEntityPairableComponent pairableComponent;

    public BlockEntityChestContainerHolderComponentImpl() {
        super(ChestContainerImpl::new);
        this.doubleChestContainer = new DoubleChestContainerImpl();
    }

    @EventHandler
    @Override
    protected void onInteract(CBlockOnInteractEvent event) {
        var interactInfo = event.getInteractInfo();
        var player = interactInfo.player();
        if (player == null || player.isSneaking() || !player.isActualPlayer()) {
            return;
        }

        var pos = new Position3i(interactInfo.clickedBlockPos(), event.getDimension());
        if (!hasSpaceAbove(pos)) {
            return;
        }

        if (pairableComponent.isPaired()) {
            var pair = pairableComponent.getPair();
            if (!hasSpaceAbove(pair.getPosition())) {
                return;
            }

            var left = getContainer();
            var right = ((BlockEntityContainerHolderComponent) pairableComponent.getPair()).getContainer();
            if (!pairableComponent.isLead()) {
                var temp = left;
                left = right;
                right = temp;
            }

            this.doubleChestContainer.setLeft(left);
            this.doubleChestContainer.setRight(right);
            this.doubleChestContainer.setBlockPos(new Position3i(interactInfo.clickedBlockPos(), event.getDimension()));
            this.doubleChestContainer.addViewer(player.getController());
        } else {
            if (this.container instanceof BlockContainer blockContainer) {
                blockContainer.setBlockPos(this.baseComponent.getPosition());
            }
            this.container.addViewer(player.getController());
        }

        event.setSuccess(true);
    }

    protected boolean hasSpaceAbove(Position3ic pos) {
        return pos.dimension().getBlockState(BlockFace.UP.offsetPos(pos)).getBlockStateData().isTransparent();
    }
}
