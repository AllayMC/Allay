package org.allaymc.api.dialog;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Consumer;

/**
 * {@code Button} represents a text action in an entity dialog, with a click callback and an
 * optional next dialog. Button handling invokes the callback before reading {@code nextDialog}, so
 * the callback may select the next menu dynamically; a failure prevents that transition.
 *
 * @author daoge_cmd
 */
public final class Button {

    /**
     * Displayed dialog button text.
     */
    @Setter
    @Getter
    private String text;
    /**
     * {@code onClick} receives this button before the next-dialog link is read. A callback failure
     * prevents the transition or close request.
     */
    @Getter
    private Consumer<Button> onClick;
    /**
     * {@code dialog} holds the owner returned by fluent configuration; {@code null} is returned
     * before this button is added to a dialog.
     */
    private Dialog dialog;
    /**
     * {@code nextDialog} selects the menu shown after a successful click callback. {@code null}
     * requests closure instead of another menu.
     */
    @Getter
    private Dialog nextDialog;

    /**
     * Creates a text button with a no-op callback and no next dialog.
     */
    public Button(String text) {
        this.text = text;
        this.onClick = button -> {
        };
    }

    /**
     * Creates a text button using the supplied click callback reference.
     */
    public Button(String text, Consumer<Button> onClick) {
        this.text = text;
        this.onClick = onClick;
    }

    /**
     * Stores the click callback and returns this button's owning dialog for configuration. The
     * result is {@code null} before attachment; callback exceptions propagate when the viewer
     * processes a button action.
     */
    public Dialog onClick(Consumer<Button> onClick) {
        this.onClick = onClick;
        return this.dialog;
    }

    /**
     * Stores the next dialog and returns this button's owning dialog for configuration. A
     * {@code null} link requests closure after the callback, and the returned owner is {@code null}
     * before attachment.
     */
    public Dialog nextDialog(Dialog nextDialog) {
        this.nextDialog = nextDialog;
        return this.dialog;
    }

    /**
     * Stores the dialog returned by this button's fluent configuration operations.
     */
    @ApiStatus.Internal
    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
}
