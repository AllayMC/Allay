package cn.allay.api.world;

/**
 * Author: LucGamesYT | daoge_cmd <br>
 * Date: 5/31/2023 <br>
 * Allay Project
 */
public enum Difficulty {

    PEACEFUL,
    EASY,
    NORMAL,
    HARD;

    public static Difficulty getDifficulty( int value ) {
        return switch ( value ) {
            case 0 -> Difficulty.PEACEFUL;
            case 1 -> Difficulty.EASY;
            case 2 -> Difficulty.NORMAL;
            default -> Difficulty.HARD;
        };
    }
}
