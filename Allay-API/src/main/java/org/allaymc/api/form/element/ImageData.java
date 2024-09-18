package org.allaymc.api.form.element;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.form.FormException;

/**
 * @author daoge_cmd
 */
@Setter
@Getter
public final class ImageData {

    public static final String PATH_TYPE = "path";
    public static final String URL_TYPE = "url";

    private String type;
    private String data;

    public ImageData(String type, String data) {
        if (!type.equals(URL_TYPE) && !type.equals(PATH_TYPE)) {
            throw new FormException("Invalid type of image data");
        }
        this.type = type;
        this.data = data;
    }
}
