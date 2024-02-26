package org.allaymc.api.form.element;

import lombok.Getter;
import lombok.Setter;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
public class Button {

    @Setter
    private String text;
    private ButtonImageData image;

    public Button(String text) {
        this.text = text;
    }

    public Button(String text, ButtonImageData image) {
        this.text = text;
        if (canSetImage(image)) {
            this.image = image;
        }
    }

    public void addImage(ButtonImageData image) {
        if (canSetImage(image)) {
            this.image = image;
        }
    }

    private boolean canSetImage(ButtonImageData image) {
        return !image.getData().isEmpty() && !image.getType().isEmpty();
    }
}
