package org.allaymc.api.dialog;

import lombok.Getter;
import org.allaymc.api.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Dialog represents a dialogue menu. This menu can consist of a title, a
 * body and up to 6 different buttons. The menu also shows a 3D render of the
 * entity that is sending the dialogue.
 *
 * @author daoge_cmd
 */
@Getter
public final class Dialog {

    private final List<Button> buttons;

    private String title;
    private String body;
    private ModelSettings modelSettings;
    private Runnable onClose;

    /**
     * Creates and returns a new instance of {@code Dialog}. The created dialog will have no title, no body,
     * and no buttons by default.
     *
     * @return a new instance of {@code Dialog}
     */
    public static Dialog create() {
        return new Dialog();
    }

    /**
     * Create a new dialog, with no title, body, and buttons.
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
     * Sets the title of this dialog.
     *
     * @param title the title to set for the dialog
     * @return the updated {@link Dialog} object for method chaining
     */
    public Dialog title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the body text of the dialog.
     *
     * @param body the body text to set
     * @return the current instance of {@link Dialog} for method chaining
     */
    public Dialog body(String body) {
        this.body = body;
        return this;
    }

    /**
     * Adds the given button to the dialog. A dialog can contain a maximum of 6 buttons.
     * The dialog instance is assigned to the button.
     *
     * @param button the button to be added to the dialog
     * @return the button that was added to the dialog
     * @throws IllegalArgumentException if more than 6 buttons are added to the dialog
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
     * Adds a new button with the specified text to the dialog.
     * If the maximum number of buttons (6) has already been added, an
     * {@link IllegalArgumentException} is thrown.
     *
     * @param text the text to display on the button
     * @return the created {@link Button} instance
     * @throws IllegalArgumentException if more than 6 buttons are added to the dialog
     */
    public Button button(String text) {
        return button(new Button(text));
    }

    /**
     * Creates a new {@code Button} instance with the specified text and click behavior,
     * then adds it to the dialog. The dialog instance is assigned to the button.
     *
     * @param text    the text to display on the button
     * @param onClick a {@link Consumer} that accepts a {@link Button} instance and defines the behavior to execute when the button is clicked
     * @return the created {@link Button} instance that was added to the dialog
     * @throws IllegalArgumentException if more than 6 buttons are added to the dialog
     */
    public Button button(String text, Consumer<Button> onClick) {
        return button(new Button(text, onClick));
    }

    /**
     * Sets the {@link ModelSettings} for this dialog, which modifies how the entity shown in the dialog is displayed,
     * including its scale, offset, and rotation.
     *
     * @param modelSettings the {@link ModelSettings} object that defines the appearance settings for the dialog entity
     * @return the current instance of {@link Dialog} for method chaining
     */
    public Dialog modelSettings(ModelSettings modelSettings) {
        this.modelSettings = modelSettings;
        return this;
    }

    /**
     * Sets a callback to be executed when the dialog is closed directly without clicking any button.
     *
     * @param onClose a {@link Runnable} that will be triggered when the dialog is closed.
     * @return the updated {@link Dialog} instance for method chaining.
     */
    public Dialog onClose(Runnable onClose) {
        this.onClose = onClose;
        return this;
    }

    /**
     * Sends this dialog to the specified {@link DialogViewer}.
     *
     * @param viewer the {@link DialogViewer} to send the dialog to
     * @param entity the entity to render in the dialog
     * @see DialogViewer#viewDialog(Dialog, Entity)
     */
    public void sendTo(DialogViewer viewer, Entity entity) {
        viewer.viewDialog(this, entity);
    }
}
