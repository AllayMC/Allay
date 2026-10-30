package org.allaymc.api.blockentity.component;

/**
 * {@code BlockEntityHopperBaseComponent} exposes the delay controlling a
 * hopper's transfer attempts. The cooldown is measured in server ticks; enabled
 * ticking decreases positive values, while a disabled hopper keeps its current
 * value until transfers are enabled again.
 *
 * @author ClexaGod
 */
public interface BlockEntityHopperBaseComponent extends BlockEntityBaseComponent {
    /**
     * Returns the current delay before another enabled hopper transfer attempt.
     */
    int getTransferCooldown();

    /**
     * Sets the transfer delay in server ticks without clamping its value.
     * Positive values count down on enabled ticks; zero and negative values
     * allow the next enabled tick to attempt a transfer.
     */
    void setTransferCooldown(int cooldown);
}
