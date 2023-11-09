package org.allaymc.api.world;

/**
 * Allay Project 2023/5/31
 *
 * @author LucGamesYT | daoge_cmd
 */
public enum Difficulty {

    PEACEFUL,
    EASY,
    NORMAL,
    HARD;

    public static Difficulty getDifficulty(int value) {
        return switch (value) {
            case 0 -> Difficulty.PEACEFUL;
            case 1 -> Difficulty.EASY;
            case 2 -> Difficulty.NORMAL;
            default -> Difficulty.HARD;
        };
    }
}
