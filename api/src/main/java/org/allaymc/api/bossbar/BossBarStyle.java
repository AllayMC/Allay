package org.allaymc.api.bossbar;

/**
 * Defines the available styles for a boss bar. Styles determine the visual segmentation of the
 * boss bar's progress bar.
 *
 * @author daoge_cmd
 */
public enum BossBarStyle {
    /**
     * Makes the boss bar solid (no segments)
     */
    SOLID,
    /**
     * Splits the boss bar into 6 segments
     */
    SEGMENTED_6,
    /**
     * Splits the boss bar into 10 segments
     */
    SEGMENTED_10,
    /**
     * Splits the boss bar into 12 segments
     */
    SEGMENTED_12,
    /**
     * Splits the boss bar into 20 segments
     */
    SEGMENTED_20
}
