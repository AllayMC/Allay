package org.allaymc.api.client.info;

/**
 * Allay Project 2023/6/23
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
