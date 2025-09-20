package org.allaymc.api.world.sound;

/**
 * Sound produced while a crossbow is being loaded.
 *
 * @param stage       the loading stage of the crossbow
 * @param quickCharge whether the item being used has the Quick Charge enchantment
 */
public record CrossbowLoadSound(LoadingStage stage, boolean quickCharge) implements Sound {
    /**
     * The stage of crossbow loading.
     */
    public enum LoadingStage {
        /**
         * The crossbow has just started loading.
         */
        START,

        /**
         * The crossbow is partially loaded.
         */
        MIDDLE,

        /**
         * The crossbow is fully loaded.
         */
        END
    }
}
