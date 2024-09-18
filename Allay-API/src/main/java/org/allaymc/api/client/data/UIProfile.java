package org.allaymc.api.client.data;

/**
 *
 * @author LucGamesYT | daoge_cmd
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
