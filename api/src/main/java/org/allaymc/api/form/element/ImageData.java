package org.allaymc.api.form.element;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@code ImageData} represents an image reference serialized with a form button or icon. An image
 * type selects a resource-pack path or remote URL, while the supplied data string is retained
 * unchanged for the client to interpret.
 *
 * @author daoge_cmd
 */
public final class ImageData {

    /**
     * Serialized image source discriminator.
     */
    @SuppressWarnings("unused")
    private final String type;
    /**
     * Image path or URL string.
     */
    @SuppressWarnings("unused")
    private final String data;

    /**
     * Creates an image reference using the supplied type's serialized token and data.
     */
    public ImageData(ImageType type, String data) {
        this.type = type.getType();
        this.data = data;
    }

    /**
     * {@code ImageType} identifies how a form image reference is resolved by the client.
     */
    @Getter
    @AllArgsConstructor
    public enum ImageType {
        /**
         * Resource-pack image path reference.
         */
        PATH("path"),
        /**
         * Remote image URL reference.
         */
        URL("url");

        /** Serialized image source token. */
        private final String type;
    }
}
