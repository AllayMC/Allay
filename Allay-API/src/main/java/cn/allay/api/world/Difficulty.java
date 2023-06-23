package cn.allay.api.world;

/**
 * @author LucGamesYT | daoge_cmd <br>
 * @date 5/31/2023 <br>
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
