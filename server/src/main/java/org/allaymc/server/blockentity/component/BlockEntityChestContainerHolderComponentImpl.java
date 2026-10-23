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
 * {@code BlockEntityChestContainerHolderComponentImpl} owns a chest's inventory
 * and a reusable combined view for its current pair. Each chest retains its own
 * stored items; the combined container forwards accesses to those inventories
 * instead of copying their contents into another store.
 *
 * <p>
 * {@code getDoubleChestContainer} places the lead chest on the left and uses
 * its position for the combined view, regardless of which chest receives the
 * request. Reconfiguration also moves the combined container's underlying
 * listeners. An unpaired call retains earlier configuration, so callers use
 * that view only while paired. Interaction checks space above both chests
 * before opening the view; ordinary persistence still saves this chest's own
 * container.
 *
 * @author IWareQ | daoge_cmd
 */
public class BlockEntityChestContainerHolderComponentImpl extends BlockEntityContainerHolderComponentImpl implements BlockEntityChestContainerHolderComponent {

    /**
     * {@code doubleChestContainer} retains the reusable paired view and its
     * listeners; an unpaired lookup leaves its existing underlying containers
     * unchanged.
     */
    protected final DoubleChestContainerImpl doubleChestContainer;

    /**
     * {@code pairableComponent} identifies the partner and lead chest used to
     * configure the shared view whenever paired container access is requested.
     */
    @Dependency
    protected BlockEntityPairableComponent pairableComponent;

    /**
     * Creates a single-chest inventory and an initially unconfigured combined
     * view. Callers can register combined-view opening listeners immediately,
     * but item access requires {@code getDoubleChestContainer} to configure a
     * valid pair first.
     */
    public BlockEntityChestContainerHolderComponentImpl() {
        super(ChestContainerImpl::new);
        this.doubleChestContainer = new DoubleChestContainerImpl();
    }

    /**
     * Returns the reusable combined view after applying the current pairing. A
     * paired lookup requires the partner to resolve to a
     * {@code BlockEntityChest}; an unpaired lookup returns the existing view
     * without clearing its previous configuration.
     *
     * <p>
     * The lead chest always supplies the left inventory and block position. A
     * request through the other chest swaps the inventories before assigning
     * the view, preserving slot identity across requests from either half. The
     * combined container manages listener migration while its sides change.
     */
    @Override
    public Container getDoubleChestContainer() {
        if (this.pairableComponent.isPaired()) {
            var blockPos = this.baseComponent.getPosition();
            var pairChest = (BlockEntityChest) this.pairableComponent.getPair();
            var left = getContainer();
            var right = pairChest.getContainer();
            // Use the lead chest for the view's first slots and opening
            // position so accessing either half presents the same combined
            // inventory ordering.
            if (!this.pairableComponent.isLead()) {
                blockPos = pairChest.getPosition();
                var temp = left;
                left = right;
                right = temp;
            }

            // Reassign both underlying containers before publishing the lead
            // position; the view's setters migrate listeners to the currently
            // selected chest inventories.
            this.doubleChestContainer.setLeft(left);
            this.doubleChestContainer.setRight(right);
            this.doubleChestContainer.setBlockPos(blockPos);
        }

        return this.doubleChestContainer;
    }

    /**
     * Returns the reusable combined view without configuring either underlying
     * side. {@link BlockEntityChestBaseComponentImpl} uses this reference to
     * register opening and closing listeners before any paired inventory access
     * occurs.
     */
    public Container getDoubleChestContainerDirectly() {
        return this.doubleChestContainer;
    }

    /**
     * Opens the applicable chest view for an actual player's interaction. A
     * single chest requires nonsolid space immediately above itself; a pair
     * also requires that space above the partner, so either obstructed half
     * prevents opening the combined inventory.
     *
     * <p>
     * Paired access refreshes the combined view before adding the viewer,
     * keeping slot ordering and the opening position tied to the lead chest.
     * The handler marks interaction successful after attempting viewer
     * addition, even when the container rejects that addition through its own
     * rules.
     */
    @EventHandler
    @Override
    protected void onInteract(CBlockOnInteractEvent event) {
        var interactInfo = event.getInteractInfo();
        var player = interactInfo.player();
        if (player == null || !player.isActualPlayer()) {
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

            getDoubleChestContainer().addViewer(player.getController());
        } else {
            if (this.container instanceof BlockContainer blockContainer) {
                blockContainer.setBlockPos(this.baseComponent.getPosition());
            }
            this.container.addViewer(player.getController());
        }

        event.setSuccess(true);
    }

    /**
     * Returns whether the block immediately above the supplied position is
     * nonsolid.
     */
    protected boolean hasSpaceAbove(Position3ic pos) {
        return !pos.dimension().getBlockState(BlockFace.UP.offsetPos(pos)).getBlockStateData().isSolid();
    }
}
