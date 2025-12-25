package org.allaymc.api.form.element;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.form.type.SimpleForm;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Consumer;

/**
 * Represents a button in a {@link SimpleForm} or {@link org.allaymc.api.form.type.CustomForm}.
 *
 * @author daoge_cmd
 */
public final class Button implements SimpleFormElement {

    @SuppressWarnings("unused")
    private final String type = "button";

    @Setter
    @Getter
    private String text;
    @Setter
    @Getter
    private ImageData image;
    @Getter
    private transient Consumer<Button> onClick = button -> {
    };
    @Getter
    private transient SimpleForm form;

    /**
     * Create a new button.
     *
     * @param text the text of the button
     */
    public Button(String text) {
        this.text = text;
    }

    /**
     * Create a new button.
     *
     * @param text  the text of the button
     * @param image the image of the button
     */
    public Button(String text, ImageData image) {
        this.text = text;
        this.image = image;
    }

    /**
     * Set the callback that will be called when the button is clicked.
     *
     * @param onClick the callback
     * @return the form
     */
    public SimpleForm onClick(Consumer<Button> onClick) {
        this.onClick = onClick;
        return form;
    }

    @ApiStatus.Internal
    public void setForm(SimpleForm form) {
        this.form = form;
    }
}
