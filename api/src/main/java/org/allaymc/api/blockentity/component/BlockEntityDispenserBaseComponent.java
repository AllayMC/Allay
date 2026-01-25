package org.allaymc.api.blockentity.component;

/**
 * @author daoge_cmd
 */
public interface BlockEntityDispenserBaseComponent extends BlockEntityBaseComponent {
    /**
     * Attempts to dispense an item from the dispenser.
     *
     * @return true if an item was successfully dispensed, false otherwise
     */
    boolean tryDispense();
}
