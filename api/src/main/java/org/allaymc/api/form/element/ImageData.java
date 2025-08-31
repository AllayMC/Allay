package org.allaymc.api.form.element;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the data of an image in a form.
 *
 * @author daoge_cmd
 */
public final class ImageData {

    @SuppressWarnings("unused")
    private final String type;
    @SuppressWarnings("unused")
    private final String data;

    /**
     * Create a new image data.
     *
     * @param type the type of the image
     * @param data the data of the image
     */
    public ImageData(ImageType type, String data) {
        this.type = type.getType();
        this.data = data;
    }

    /**
     * Represents the type of image data.
     */
    @Getter
    @AllArgsConstructor
    public enum ImageType {
        /**
         * Image is stored in the resources pack.
         */
        PATH("path"),
        /**
         * Image is stored in the URL.
         */
        URL("url");

        private final String type;
    }
}
