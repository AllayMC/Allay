package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.OffhandContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;

import java.util.function.Supplier;

/**
 * {@code OffhandContainerImpl} stores a player's offhand stack and exposes its fixed
 * built-in inventory identity. Ordinary player-container notifications synchronize the
 * owning controller, and a slot listener also publishes offhand equipment to entity
 * viewers using the retained owner supplier.
 *
 * @author daoge_cmd
 */
public class OffhandContainerImpl extends AbstractPlayerContainer implements OffhandContainer {

    /**
     * Creates empty player offhand storage and registers the equipment listener on slot
     * zero. The supplied resolver must return a player before a slot notification
     * invokes that listener.
     */
    public OffhandContainerImpl(Supplier<EntityPlayer> playerSupplier) {
        super(ContainerTypes.OFFHAND, playerSupplier);
        addSlotChangeListener(0, this::onOffhandChange);
    }

    /**
     * Returns the fixed offhand inventory ID used for built-in content updates.
     */
    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.OFFHAND;
    }

    /**
     * Publishes the player's current offhand equipment to entity viewers. The callback
     * ignores {@code newItemStack} and directly uses the resolved player, so the owner
     * must already be available.
     */
    protected void onOffhandChange(ItemStack newItemStack) {
        var player = playerSupplier.get();
        player.forEachViewers(viewer -> viewer.viewEntityOffhand(player));
    }
}
