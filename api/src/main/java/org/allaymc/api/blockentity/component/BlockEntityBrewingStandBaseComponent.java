package org.allaymc.api.blockentity.component;

/**
 * @author IWareQ
 */
public interface BlockEntityBrewingStandBaseComponent extends BlockEntityBaseComponent {
    int getBrewTime();

    void setBrewTime(int time);

    int getFuelAmount();

    void setFuelAmount(int amount);

    int getFuelTotal();

    void setFuelTotal(int amount);
}
