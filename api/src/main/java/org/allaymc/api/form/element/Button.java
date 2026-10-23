package org.allaymc.api.form.element;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.form.type.SimpleForm;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Consumer;

/**
 * {@code Button} represents a selectable simple-form action with text and an optional image. The
 * client returns its button index, and response handling invokes the simple form's shared callback
 * before this button's callback, storing the successful response afterward.
 *
 * @author daoge_cmd
 */
public final class Button implements SimpleFormElement {

    /**
     * Serialized form element discriminator.
     */
    @SuppressWarnings("unused")
    private final String type = "button";

    /**
     * Displayed form element text.
     */
    @Setter
    @Getter
    private String text;
    /**
     * Optional button image reference.
     */
    @Setter
    @Getter
    private ImageData image;
    /**
     * {@code onClick} receives this button after the form's shared response callback succeeds. A
     * callback failure prevents storage of the form response.
     */
    @Getter
    private transient Consumer<Button> onClick = button -> {
    };
    /**
     * {@code form} supplies the owner returned by callback configuration. {@code null} indicates
     * that this button has not been attached to a simple form.
     */
    @Getter
    private transient SimpleForm form;

    /**
     * Creates a simple-form button with text and a no-op click callback.
     */
    public Button(String text) {
        this.text = text;
    }

    /**
     * Creates a simple-form button with text, an image, and a no-op callback.
     */
    public Button(String text, ImageData image) {
        this.text = text;
        this.image = image;
    }

    /**
     * Stores the click callback and returns the owning simple form for configuration. The result is
     * {@code null} before attachment; a callback failure propagates and prevents storage of the
     * successful form response.
     */
    public SimpleForm onClick(Consumer<Button> onClick) {
        this.onClick = onClick;
        return form;
    }

    /**
     * Stores the simple form returned by this button's callback configuration.
     */
    @ApiStatus.Internal
    public void setForm(SimpleForm form) {
        this.form = form;
    }
}
