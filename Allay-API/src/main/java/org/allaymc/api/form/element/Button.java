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
public class Button {

    private String text;
    private ButtonImageData image;

    public Button(String text) {
        this.text = text;
    }

    public Button(String text, ButtonImageData image) {
        this.text = text;
        this.image = image;
    }
}
