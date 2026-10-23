package org.allaymc.api.dialog;

import lombok.Getter;
import org.allaymc.api.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * {@code Dialog} represents an entity dialog containing a title, body, model settings, and text
 * buttons. Button actions can link to another dialog for menu transitions. {@code button} limits
 * additions to six entries, while the generated list getter exposes the mutable backing list.
 *
 * @author daoge_cmd
 */
@Getter
public final class Dialog {

    /**
     * {@code buttons} holds actions in display order. {@code getButtons()} exposes this mutable
     * list, so direct changes bypass the six-button check and owner assignment.
     */
    private final List<Button> buttons;

    /**
     * Dialog window title text.
     */
    private String title;
    /**
     * Dialog main body text.
     */
    private String body;
    /**
     * Entity model display settings.
     */
    private ModelSettings modelSettings;
    /**
     * {@code onClose} runs when a final client close notification is processed for the stored
     * dialog. Explicit server closure removes that dialog reference first.
     */
    private Runnable onClose;

    /**
     * Creates a new dialog with no buttons and default model settings.
     */
    public static Dialog create() {
        return new Dialog();
    }

    /**
     * Creates a dialog with empty title and body text and no buttons. The model uses the shared
     * {@code ModelSettings.DEFAULT} reference, and the initial close callback performs no action.
     */
    public Dialog() {
        this.buttons = new ArrayList<>();
        this.title = "";
        this.body = "";
        this.modelSettings = ModelSettings.DEFAULT;
        this.onClose = () -> {
        };
    }

    /**
     * Stores the title text and returns this dialog for configuration.
     */
    public Dialog title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Stores the body text and returns this dialog for configuration.
     */
    public Dialog body(String body) {
        this.body = body;
        return this;
    }

    /**
     * Adds a button, assigns this dialog as its owner, and returns the attached button. Six
     * existing entries reject the addition before owner assignment; adding the same button again is
     * otherwise allowed.
     *
     * @throws IllegalArgumentException if six buttons are already present
     */
    public Button button(Button button) {
        if (this.buttons.size() >= 6) {
            throw new IllegalArgumentException("Cannot add more than 6 buttons to a dialog");
        }

        button.setDialog(this);
        this.buttons.add(button);
        return button;
    }

    /**
     * Creates a button with the supplied text, adds it to this dialog, and returns the attached
     * instance. Six existing entries reject attachment; equal labels are allowed because each call
     * creates a separate button.
     */
    public Button button(String text) {
        return button(new Button(text));
    }

    /**
     * Creates a button with the supplied text, adds it to this dialog, and returns the attached
     * instance. Six existing entries reject attachment; equal labels are allowed because each call
     * creates a separate button.
     */
    public Button button(String text, Consumer<Button> onClick) {
        return button(new Button(text, onClick));
    }

    /**
     * Stores the model settings reference and returns this dialog for configuration.
     */
    public Dialog modelSettings(ModelSettings modelSettings) {
        this.modelSettings = modelSettings;
        return this;
    }

    /**
     * Stores the callback for a final client close notification and returns this dialog. The
     * processor removes the stored dialog before invocation; direct server closure removes it
     * without invoking this callback.
     */
    public Dialog onClose(Runnable onClose) {
        this.onClose = onClose;
        return this;
    }

    /**
     * Displays this dialog through the supplied viewer using the supplied entity model. The viewer
     * retains the same dialog reference, so later button requests are handled against this mutable
     * configuration.
     */
    public void sendTo(DialogViewer viewer, Entity entity) {
        viewer.viewDialog(this, entity);
    }
}
