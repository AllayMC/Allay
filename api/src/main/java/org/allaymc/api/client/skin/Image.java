package org.allaymc.api.client.skin;

/**
 * @author LucGamesYT | daoge_cmd
 */
public record Image(int width, int height, byte[] data) {
    /**
     * Creates an Image object based on the provided byte array.
     * The size of the byte array determines the dimensions of the image.
     *
     * @param data The byte array containing the image data.
     *
     * @return An Image object with the appropriate dimensions and data, or {@code null} if the byte array size is not recognized.
     */
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
