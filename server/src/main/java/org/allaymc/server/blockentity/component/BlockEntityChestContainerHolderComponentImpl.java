package org.allaymc.server.blockentity.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.blockentity.component.BlockEntityChestContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityPairableComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityChest;
import org.allaymc.api.container.Container;
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
public class BlockEntityChestContainerHolderComponentImpl extends BlockEntityContainerHolderComponentImpl implements BlockEntityChestContainerHolderComponent {

    protected final DoubleChestContainerImpl doubleChestContainer;

    @Dependency
    protected BlockEntityPairableComponent pairableComponent;

    public BlockEntityChestContainerHolderComponentImpl() {
        super(ChestContainerImpl::new);
        this.doubleChestContainer = new DoubleChestContainerImpl();
    }

    @Override
    public Container getDoubleChestContainer() {
        if (this.pairableComponent.isPaired()) {
            // Update the double chest container object if this chest is paired
            var blockPos = this.baseComponent.getPosition();
            var pairChest = (BlockEntityChest) this.pairableComponent.getPair();
            var left = getContainer();
            var right = pairChest.getContainer();
            if (!this.pairableComponent.isLead()) {
                // Block pos is always the pos of the lead chest
                blockPos = pairChest.getPosition();
                // Swap left and right container if the current chest is not the lead chest
                var temp = left;
                left = right;
                right = temp;
            }

            this.doubleChestContainer.setLeft(left);
            this.doubleChestContainer.setRight(right);
            this.doubleChestContainer.setBlockPos(blockPos);
        }

        return this.doubleChestContainer;
    }

    // Used in BlockEntityChestBaseComponentImpl
    public Container getDoubleChestContainerDirectly() {
        return this.doubleChestContainer;
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
            if (!hasSpaceAbove(pairableComponent.getPair().getPosition())) {
                return;
            }

            // Use getDoubleChestContainer() method here which will update the double chest container object
            getDoubleChestContainer().addViewer(player.getController());
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
