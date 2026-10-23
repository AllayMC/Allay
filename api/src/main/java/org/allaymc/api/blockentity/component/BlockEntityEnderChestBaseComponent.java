package org.allaymc.api.blockentity.component;

/**
 * {@code BlockEntityEnderChestBaseComponent} exposes the viewer count used for
 * an ender chest's world animation. Player-owned containers update this count
 * on opening and closing; the block entity uses assigned counts of one and zero
 * to broadcast the respective state changes.
 *
 * @author IWareQ
 */
public interface BlockEntityEnderChestBaseComponent extends BlockEntityBaseComponent {
    /**
     * Returns the current world-side viewer count for this ender chest.
     */
    int getViewersCount();

    /**
     * Assigns the ender chest's viewer count and signals applicable animation
     * states. Assigning {@code 1} opens the chest and assigning {@code 0}
     * closes it; repeated assignments can repeat sounds, and the count is not
     * clamped.
     */
    void setViewersCount(int count);
}
