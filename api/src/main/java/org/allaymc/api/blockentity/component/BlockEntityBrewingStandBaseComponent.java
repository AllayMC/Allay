package org.allaymc.api.blockentity.component;

/**
 * @author IWareQ
 */
public interface BlockEntityBrewingStandBaseComponent extends BlockEntityBaseComponent {
    /**
     * Get the brew time of this brewing stand.
     *
     * @return the brew time of this brewing stand
     */
    int getBrewTime();

    /**
     * Set the brew time of this brewing stand.
     *
     * @param time the brew time of this brewing stand
     */
    void setBrewTime(int time);

    /**
     * Get the fuel amount of this brewing stand.
     *
     * @return the fuel amount of this brewing stand
     */
    int getFuelAmount();

    /**
     * Set the fuel amount of this brewing stand.
     *
     * @param amount the fuel amount of this brewing stand
     */
    void setFuelAmount(int amount);

    /**
     * Get the total fuel of this brewing stand.
     *
     * @return the total fuel of this brewing stand
     */
    int getFuelTotal();

    /**
     * Set the total fuel of this brewing stand.
     *
     * @param amount the total fuel of this brewing stand
     */
    void setFuelTotal(int amount);
}
