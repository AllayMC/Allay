package org.allaymc.api.blockentity.component;

/**
 * @author IWareQ
 */
public interface BlockEntityEnderChestBaseComponent extends BlockEntityBaseComponent {
    /**
     * Get the viewers count of this ender chest.
     *
     * @return the viewers count of this ender chest
     */
    int getViewersCount();

    /**
     * Set the viewers count of this ender chest.
     *
     * @param count the viewers count of this ender chest
     */
    void setViewersCount(int count);
}
