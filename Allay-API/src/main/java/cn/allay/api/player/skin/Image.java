package cn.allay.api.player.skin;

/**
 * Allay Project 2023/6/23
 *
 * @author LucGamesYT | daoge_cmd
 */
public record Image(int width, int height, byte[] data) {

    public static Image getImage(byte[] data) {
        return switch (data.length) {
            case Skin.SINGLE_SKIN_SIZE -> new Image(64, 32, data);
            case Skin.DOUBLE_SKIN_SIZE -> new Image(64, 64, data);
            case Skin.SKIN_128_64_SIZE -> new Image(128, 64, data);
            case Skin.SKIN_128_128_SIZE -> new Image(128, 128, data);
            default -> null;
        };
    }
}
