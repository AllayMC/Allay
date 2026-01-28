package org.allaymc.server.container.impl;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.function.Supplier;

/**
 * PlayerContainer is a type of container that is always opened to the player, including the inventory,
 * armor and offhand containers. These container types have their special "unopened container id" which
 * can be found in {@link UnopenedContainerId}.
 *
 * @author daoge_cmd
 * @see UnopenedContainerId
 */
public abstract class AbstractPlayerContainer extends BaseContainer {

    protected Supplier<EntityPlayer> playerSupplier;

    public AbstractPlayerContainer(ContainerType<? extends Container> containerType, Supplier<EntityPlayer> playerSupplier) {
        super(containerType);
        this.playerSupplier = playerSupplier;
    }

    @Override
    public void notifySlotChange(int slot, boolean send) {
        super.notifySlotChange(slot, send);
        if (send) {
            // Because even if the client has not opened the player container, they can always see their own hot bar.
            // Therefore, we need to send an inventory packet to the client as well.
            var player = playerSupplier.get();
            // The player object we get may be empty because some container types can be used by non-player entities (e.g. zombie)
            if (player != null && player.isActualPlayer() && !viewers.containsValue(player.getController())) {
                player.getController().viewContainerSlot(this, slot);
            }
        }
    }

    /**
     * Get the unopened container id. The id can be found in {@link UnopenedContainerId}.
     *
     * @return the unopened container id
     */
    public abstract int getUnopenedContainerId();
}
