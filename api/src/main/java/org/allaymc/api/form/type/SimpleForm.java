package org.allaymc.api.form.type;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.form.FormCancelReason;
import org.allaymc.api.form.element.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * {@code SimpleForm} represents a JSON form with a title, body, selectable buttons, and decorative
 * entries. The element list preserves display order, while the returned client selection counts
 * only buttons, so decorative entries do not consume selection indexes.
 *
 * <p>
 * {@code getButton} translates the button ordinal back to the retained element instance. Invalid or
 * nonnumeric selections log a warning without replacing {@code response}. For a valid selection,
 * the shared response callback runs before the button callback, and response storage follows both
 * callbacks. A callback failure therefore preserves the previous response even though earlier
 * callback side effects may already have occurred.
 *
 * @author daoge_cmd
 */
@Getter
@Slf4j
public class SimpleForm extends Form {
    /**
     * Serialized simple-form layout discriminator.
     */
    @SuppressWarnings("unused")
    private final String type = "form";

    /**
     * {@code elements} retains entries in display order. The generated getter exposes the mutable
     * list used for serialization and button-index resolution.
     */
    private final List<SimpleFormElement> elements = new ArrayList<>();
    /**
     * Displayed form window title.
     */
    private String title = "";
    /**
     * Displayed main body text.
     */
    private String content = "";
    /**
     * {@code onResponse} receives the selected button before its own callback runs. Failure
     * prevents that callback and storage of the selected response.
     */
    private transient Consumer<Button> onResponse = button -> {
    };

    /**
     * Stores the title text and returns this simple form for configuration.
     */
    public SimpleForm title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Stores the body text and returns this simple form for configuration.
     */
    public SimpleForm content(String content) {
        this.content = content;
        return this;
    }

    /**
     * Attaches the supplied button to this form and returns that same button.
     */
    public Button button(Button button) {
        button.setForm(this);
        elements.add(button);
        return button;
    }

    /**
     * Appends a new text button and returns it for callback configuration.
     */
    public Button button(String text) {
        return button(new Button(text));
    }

    /**
     * Appends an image button using the supplied image type and data reference.
     */
    public Button button(String text, ImageData.ImageType imageType, String data) {
        return button(new Button(text, new ImageData(imageType, data)));
    }

    /**
     * Appends a button with a resource-pack path image and returns it.
     */
    public Button buttonWithPathImage(String text, String path) {
        return button(new Button(text, new ImageData(ImageData.ImageType.PATH, path)));
    }

    /**
     * Appends a button with a remote URL image and returns it.
     */
    public Button buttonWithUrlImage(String text, String url) {
        return button(new Button(text, new ImageData(ImageData.ImageType.URL, url)));
    }

    /**
     * Appends a decorative text label and returns this form for configuration.
     */
    public SimpleForm label(String text) {
        this.elements.add(new Label(text));
        return this;
    }

    /**
     * Appends a decorative text heading and returns this form for configuration.
     */
    public SimpleForm header(String text) {
        this.elements.add(new Header(text));
        return this;
    }

    /**
     * Appends a decorative divider entry and returns this form for configuration.
     */
    public SimpleForm divider() {
        this.elements.add(new Divider());
        return this;
    }

    /**
     * Stores the shared callback for valid button selections and returns this form. The callback
     * runs before the selected button's callback, and a failure prevents that callback and final
     * response storage.
     */
    public SimpleForm onResponse(Consumer<Button> onResponse) {
        this.onResponse = onResponse;
        return this;
    }

    /**
     * Stores the inherited cancellation callback and returns this simple form for configuration.
     * Cancellation preserves any previously stored button response, and callback exceptions
     * propagate to the operation reporting closure.
     */
    @Override
    public SimpleForm onClose(Runnable onClose) {
        return (SimpleForm) super.onClose(onClose);
    }

    /**
     * Resolves a client button ordinal, invokes its callbacks, and stores the selected button after
     * success. Nonnumeric and out-of-range selections log warnings and preserve the previous
     * response.
     *
     * <p>
     * Resolution counts only buttons in the element list, preserving the client selection when
     * decorative entries are present. The shared callback runs before the selected button's
     * callback; failure in either callback propagates without storing the new selection or undoing
     * earlier application side effects.
     */
    @Override
    public void handleResponse(String data) {
        int buttonIndex;
        try {
            buttonIndex = Integer.parseInt(data);
        } catch (Exception e) {
            // Invalid numeric input consumes no selection and leaves the previous
            // response available, rather than dispatching a callback for a guessed button.
            log.warn("Invalid simple form response: {}", data);
            return;
        }
        var button = getButton(buttonIndex);
        if (button != null) {
            onResponse.accept(button);
            button.getOnClick().accept(button);
            response = button;
        } else {
            log.warn("Viewer clicked an invalid simple form element: {}", data);
        }
    }

    /**
     * Invokes the configured callback for the supplied cancellation reason without changing the
     * stored response. Callback exceptions propagate, and a previously selected button remains
     * available through {@code getResponse}.
     */
    @Override
    public void handleClose(FormCancelReason reason) {
        onClose.accept(reason);
    }

    /**
     * Returns the selected button retained after both response callbacks complete successfully. The
     * result is {@code null} before such a selection, and invalid responses or cancellation
     * preserve any earlier successful selection.
     */
    @Override
    public Button getResponse() {
        return response != null ? (Button) response : null;
    }

    /**
     * Resolves a zero-based button ordinal to the retained button instance. Labels, headers, and
     * dividers remain in display order but do not decrement the ordinal.
     *
     * <p>
     * This translation keeps the client's button-only numbering separate from indexes into
     * {@code elements}, so adding decorative content does not associate a response with the wrong
     * control. Negative or excessive ordinals exhaust the traversal and return {@code null} rather
     * than selecting an element.
     */
    private Button getButton(int buttonIndex) {
        for (var element : elements) {
            // The client numbers buttons rather than layout entries, so decorative
            // content must not shift the ordinal used to identify the selected action.
            if (element instanceof Button button) {
                if (buttonIndex == 0) {
                    return button;
                }

                buttonIndex--;
            }
        }

        return null;
    }
}
