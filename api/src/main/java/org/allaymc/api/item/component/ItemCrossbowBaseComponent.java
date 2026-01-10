package org.allaymc.api.item.component;

import org.allaymc.api.item.ItemStack;

/**
 * Component interface for crossbow items.
 *
 * @author daoge_cmd
 */
public interface ItemCrossbowBaseComponent extends ItemBaseComponent {

    /**
     * Checks if the crossbow has a loaded projectile.
     *
     * @return {@code true} if the crossbow is loaded with a projectile, {@code false} otherwise
     */
    boolean isLoaded();

    /**
     * Gets the projectile that is currently loaded in the crossbow.
     *
     * @return the loaded projectile {@link ItemStack}, or {@code null} if not loaded
     */
    ItemStack getLoadedProjectile();

    /**
     * Sets the projectile loaded in the crossbow.
     *
     * @param projectile the {@link ItemStack} projectile to load, or {@code null} to unload
     */
    void setLoadedProjectile(ItemStack projectile);
}
