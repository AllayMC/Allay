package org.allaymc.api.world.data;

/**
 * {@code Difficulty} names the four gameplay difficulty levels supported by world metadata.
 * Numeric conversion maps the serialized values zero through three explicitly,
 * while unsupported integers return {@code null} so callers can distinguish invalid
 * input from a valid difficulty choice.
 *
 * @author LucGamesYT | daoge_cmd
 */
public enum Difficulty {
    /**
     * Peaceful gameplay difficulty level.
     */
    PEACEFUL,
    /**
     * Easy gameplay difficulty level.
     */
    EASY,
    /**
     * Normal gameplay difficulty level.
     */
    NORMAL,
    /**
     * Hard gameplay difficulty level.
     */
    HARD;

    /**
     * Returns the difficulty represented by integer zero through three, or {@code null}
     * for any other value. Unknown input is not clamped or replaced with a
     * default difficulty by this conversion.
     */
    public static Difficulty from(int value) {
        return switch (value) {
            case 0 -> Difficulty.PEACEFUL;
            case 1 -> Difficulty.EASY;
            case 2 -> Difficulty.NORMAL;
            case 3 -> Difficulty.HARD;
            // Unknown serialized values stay distinguishable from every valid
            // difficulty, allowing the caller to choose its own rejection or
            // configured-default policy.
            default -> null;
        };
    }
}
