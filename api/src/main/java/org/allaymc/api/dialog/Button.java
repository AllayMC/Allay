package org.allaymc.api.dialog;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Consumer;

/**
 * Button represents a button added to a {@link Dialog} menu and consists of just text.
 *
 * @author daoge_cmd
 */
public final class Button {

    /**
     * The text of the button.
     */
    @Setter
    @Getter
    private String text;
    /**
     * The callback function to be executed when the button is clicked.
     */
    @Getter
    private Consumer<Button> onClick;
    /**
     * The dialog associated with the button.
     */
    private Dialog dialog;
    /**
     * The next dialog to be displayed when the button is clicked. If no next dialog is set ({@code null}), the dialog will be closed
     * after the button is clicked.
     */
    @Getter
    private Dialog nextDialog;

    /**
     * Create a new button.
     *
     * @param text the text of the button
     */
    public Button(String text) {
        this.text = text;
        this.onClick = button -> {
        };
    }

    /**
     * Sets a callback function to be executed when the button is clicked.
     *
     * @param onClick a {@link Consumer} that accepts a {@link Button} instance and defines the behavior to execute
     *                when the button is clicked
     * @return the {@link Dialog} instance associated with the button, allowing method chaining for dialog configuration
     */
    public Dialog onClick(Consumer<Button> onClick) {
        this.onClick = onClick;
        return this.dialog;
    }

    /**
     * Sets the next dialog to be displayed when the button is clicked. If no next dialog is set, the dialog will be closed
     * after the button is clicked.
     *
     * @param nextDialog the next dialog to be displayed
     * @return the {@link Dialog} instance associated with the button, allowing method chaining for dialog configuration
     */
    public Dialog nextDialog(Dialog nextDialog) {
        this.nextDialog = nextDialog;
        return this.dialog;
    }

    @ApiStatus.Internal
    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
}
