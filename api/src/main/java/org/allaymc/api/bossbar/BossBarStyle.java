package org.allaymc.api.bossbar;

/**
 * {@code BossBarStyle} identifies the segmentation used for a boss bar. A bar combines one style
 * with its progress fraction, and viewers use that style when displaying its filled portion. A
 * solid style has no segment divisions.
 *
 * @author daoge_cmd
 */
public enum BossBarStyle {
    /**
     * Continuous bar without segment divisions.
     */
    SOLID,
    /**
     * Progress bar divided into 6 segments.
     */
    SEGMENTED_6,
    /**
     * Progress bar divided into 10 segments.
     */
    SEGMENTED_10,
    /**
     * Progress bar divided into 12 segments.
     */
    SEGMENTED_12,
    /**
     * Progress bar divided into 20 segments.
     */
    SEGMENTED_20
}
