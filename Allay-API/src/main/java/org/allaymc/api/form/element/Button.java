package org.allaymc.api.form.element;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.form.type.SimpleForm;

import java.util.function.Consumer;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Setter
@Getter
public final class Button {

    private String text;
    private ImageData image;
    private transient Consumer<Button> onClick = button -> {};
    private transient SimpleForm form;

    public Button(String text) {
        this.text = text;
    }

    public Button(String text, ImageData image) {
        this.text = text;
        this.image = image;
    }

    public SimpleForm onClick(Consumer<Button> onClick) {
        this.onClick = onClick;
        return form;
    }

    public SimpleForm ignore() {
        return form;
    }
}
