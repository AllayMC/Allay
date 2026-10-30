package org.allaymc.server.container.impl;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.function.Supplier;

/**
 * {@code AbstractPlayerContainer} exposes player-owned storage that can be synchronized
 * without an explicitly opened container window. Subclasses supply the fixed inventory
 * ID used for built-in content updates, while the player supplier resolves the current
 * entity when notification occurs. Ordinary container listeners still run through the
 * base implementation.
 *
 * <p>
 * A player can see built-in contents while its viewer membership is absent. After
 * ordinary notifications, this class sends an additional controller update only when
 * sending is enabled, the owner is an actual player, and the controller is then absent
 * from the viewer map. When listeners change membership during the base notification,
 * that later test can skip an owner update or send a duplicate; deduplication relies on
 * membership remaining stable during callbacks.
 *
 * @author daoge_cmd
 */
public abstract class AbstractPlayerContainer extends BaseContainer {

    /**
     * {@code playerSupplier} resolves the current owner during notification. It may
     * return {@code null}, in which case the additional owner update is skipped.
     */
    protected Supplier<EntityPlayer> playerSupplier;

    /**
     * Creates player-oriented storage using the supplied type and owner resolver. The
     * supplier is retained for later notifications rather than evaluated during
     * construction, allowing an owner to become available afterward.
     */
    public AbstractPlayerContainer(ContainerType<? extends Container> containerType, Supplier<EntityPlayer> playerSupplier) {
        super(containerType);
        this.playerSupplier = playerSupplier;
    }

    /**
     * Publishes ordinary slot notifications and then checks whether the owner needs an
     * additional built-in update. The base operation runs first, including slot
     * listeners even when {@code send} is {@code false}.
     *
     * <p>
     * The extra update requires {@code send}, a non-{@code null} actual player, and a
     * controller absent from the viewer map after those callbacks. Stable membership
     * avoids repeating an owner update already sent by the base path. Callback changes
     * to membership can instead cause a skipped or duplicate owner update because the
     * final test does not record who previously received a packet.
     */
    @Override
    public void notifySlotChange(int slot, boolean send) {
        super.notifySlotChange(slot, send);
        if (send) {
            // Built-in inventory remains visible without a window. Check current
            // membership after callbacks to choose an extra owner update; membership
            // changes can affect deduplication.
            var player = playerSupplier.get();
            if (player != null && player.isActualPlayer() && !viewers.containsValue(player.getController())) {
                player.getController().viewContainerSlot(this, slot);
            }
        }
    }

    /**
     * Returns the fixed protocol inventory ID used without an explicit window.
     */
    public abstract int getUnopenedContainerId();
}
