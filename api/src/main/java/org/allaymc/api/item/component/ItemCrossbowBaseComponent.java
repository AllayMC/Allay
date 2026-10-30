package org.allaymc.api.item.component;

import org.allaymc.api.item.ItemStack;

/**
 * {@code ItemCrossbowBaseComponent} exposes the projectile retained by a crossbow
 * and determines whether that projectile makes the item loaded. Loading stores an
 * item stack for a later shot; clearing the projectile unloads the crossbow
 * without firing it.
 *
 * @author daoge_cmd
 */
public interface ItemCrossbowBaseComponent extends ItemBaseComponent {

    /**
     * Returns whether the retained projectile is a nonempty, non-air stack.
     * A stored {@code null}, empty stack, or air stack produces {@code false}, so
     * retaining a projectile reference alone does not establish a usable load.
     */
    boolean isLoaded();

    /**
     * Returns the currently retained projectile stack, or {@code null} when none is
     * stored. The returned stack is the retained reference, so changes to that
     * stack also affect the crossbow's subsequent loaded-state check and shot.
     */
    ItemStack getLoadedProjectile();

    /**
     * Stores the projectile reference for a later crossbow shot.
     * The component retains {@code projectile} directly; callers can pass
     * {@code null} to unload it and must supply a supported projectile for firing.
     */
    void setLoadedProjectile(ItemStack projectile);
}
