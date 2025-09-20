package org.allaymc.api.world.data;

/**
 * Difficulty enum contains all valid difficulties in the game.
 *
 * @author LucGamesYT | daoge_cmd
 */
public enum Difficulty {
    PEACEFUL,
    EASY,
    NORMAL,
    HARD;

    /**
     * Get difficulty from int value.
     *
     * @param value int value of difficulty
     * @return difficulty, or {@code null} if not found.
     */
    public static Difficulty from(int value) {
        return switch (value) {
            case 0 -> Difficulty.PEACEFUL;
            case 1 -> Difficulty.EASY;
            case 2 -> Difficulty.NORMAL;
            case 3 -> Difficulty.HARD;
            default -> null;
        };
    }
}
