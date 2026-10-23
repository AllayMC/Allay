package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.item.ItemStack;

/**
 * {@code EntityHandContainer} exposes the item occupying the entity main-hand slot. The
 * hand accessors read and replace container storage, while ordinary slot notifications
 * allow implementations to update equipment shown to entity viewers.
 *
 * @author daoge_cmd
 */
public interface EntityHandContainer extends Container {

    /**
     * Returns the stack occupying the entity main-hand slot. The stack remains shared
     * with the container, so direct changes require the corresponding slot notification
     * to update viewers.
     */
    ItemStack getItemInHand();

    /**
     * Replaces the stack occupying the entity main-hand slot and notifies container
     * observers. The supplied stack must be non-{@code null} and remains shared with the
     * container after assignment.
     */
    void setItemInHand(ItemStack itemStack);

    /**
     * Clears the entity main-hand slot by installing the shared air stack. The ordinary
     * slot notification path also updates container viewers and any registered equipment
     * listeners.
     */
    void clearItemInHand();
}
