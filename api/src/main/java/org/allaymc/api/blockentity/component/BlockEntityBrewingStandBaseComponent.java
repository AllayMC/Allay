package org.allaymc.api.blockentity.component;

/**
 * {@code BlockEntityBrewingStandBaseComponent} exposes a brewing stand's
 * countdown and fuel counters. Brewing progress is measured in server ticks,
 * while fuel tracks remaining brew operations and the total used for the client
 * display; setters replace these counters directly.
 *
 * @author IWareQ
 */
public interface BlockEntityBrewingStandBaseComponent extends BlockEntityBaseComponent {
    /**
     * Returns the remaining brewing countdown measured in server ticks.
     */
    int getBrewTime();

    /**
     * Sets the remaining brewing countdown measured in server ticks. The
     * implementation stores {@code time} directly; the tick logic interprets
     * nonpositive values as eligible to complete an otherwise valid brew.
     */
    void setBrewTime(int time);

    /**
     * Returns the remaining number of brew operations supplied by fuel.
     */
    int getFuelAmount();

    /**
     * Sets the remaining number of fuel-supported brew operations directly.
     * Nonpositive values make the next processing attempt check the fuel slot;
     * this setter does not consume or replenish any inventory item.
     */
    void setFuelAmount(int amount);

    /**
     * Returns the total fuel counter displayed alongside remaining brew
     * operations.
     */
    int getFuelTotal();

    /**
     * Sets the total fuel counter reported to brewing-stand viewers. This
     * display value is stored separately from remaining fuel; changing it does
     * not change {@code getFuelAmount()} or consume an inventory item.
     */
    void setFuelTotal(int amount);
}
