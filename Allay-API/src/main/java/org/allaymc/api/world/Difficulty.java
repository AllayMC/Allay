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

    public static Difficulty from(int value) {
        return switch (value) {
            case 0 -> Difficulty.PEACEFUL;
            case 1 -> Difficulty.EASY;
            case 2 -> Difficulty.NORMAL;
            case 3 -> Difficulty.HARD;
            default -> null;
        };
    }

    public static Difficulty from(String value) {
        return switch (value) {
            case "peaceful", "p" -> Difficulty.PEACEFUL;
            case "easy", "e" -> Difficulty.EASY;
            case "normal", "n" -> Difficulty.NORMAL;
            case "hard", "h" -> Difficulty.HARD;
            default -> null;
        };
    }
}
