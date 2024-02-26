package org.allaymc.api.form.element;

import lombok.Getter;
import lombok.Setter;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Setter
@Getter
public class ButtonImageData {

    public static final String IMAGE_DATA_TYPE_PATH = "path";
    public static final String IMAGE_DATA_TYPE_URL = "url";

    private String type;
    private String data;

    public ButtonImageData(String type, String data) {
        if (!type.equals(IMAGE_DATA_TYPE_URL) && !type.equals(IMAGE_DATA_TYPE_PATH)) return;
        this.type = type;
        this.data = data;
    }
}
