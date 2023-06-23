package cn.allay.api.player.info;

/**
 * @author LucGamesYT | daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
public enum UIProfile {

    CLASSIC,
    POCKET;

    public static UIProfile getById(int id) {
        return switch (id) {
            case 0 -> CLASSIC;
            case 1 -> POCKET;
            default -> null;
        };
    }

}
