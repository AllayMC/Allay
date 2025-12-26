package org.allaymc.api.blockentity.component;

/**
 * @author ClexaGod
 */
public interface BlockEntityHopperBaseComponent extends BlockEntityBaseComponent {
    /**
     * Gets the transfer cooldown in ticks.
     *
     * @return the transfer cooldown
     */
    int getTransferCooldown();

    /**
     * Sets the transfer cooldown in ticks.
     *
     * @param cooldown the transfer cooldown
     */
    void setTransferCooldown(int cooldown);
}
