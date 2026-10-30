package org.allaymc.api.world.sound;

/**
 * {@code CrossbowLoadSound} describes one stage of loading a crossbow. The
 * runtime combines {@code stage} with {@code quickCharge} to select the
 * corresponding ordinary or Quick Charge sound; this descriptor does not
 * advance the weapon's loading state.
 *
 * @param stage crossbow loading sound stage
 *
 * @param quickCharge whether to select Quick Charge sounds
 */
public record CrossbowLoadSound(LoadingStage stage, boolean quickCharge) implements Sound {
    /**
     * {@code LoadingStage} identifies the loading phase used to select a crossbow sound.
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
