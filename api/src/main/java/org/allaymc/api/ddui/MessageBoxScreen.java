package org.allaymc.api.ddui;

import org.allaymc.api.ddui.element.MessageBoxButtonElement;
import org.allaymc.api.player.Player;

import java.util.function.Consumer;

/**
 * DDUI implementation of the Bedrock message box screen.
 *
 * @author Miroshka
 */
public final class MessageBoxScreen extends DataDrivenScreen {

    private static final String TITLE_BINDING = "binding:title";
    private static final String BODY_BINDING = "binding:body";

    @Override
    public String getIdentifier() {
        return "minecraft:message_box";
    }

    @Override
    public String getPropertyName() {
        return "message_box_data";
    }

    /**
     * Sets a static title for the message box.
     *
     * @param title screen title
     *
     * @return this screen
     */
    public MessageBoxScreen title(String title) {
        unbind(TITLE_BINDING);
        writeStringProperty("title", title);
        return this;
    }

    /**
     * Binds the message box title to an observable value.
     *
     * @param title observable title
     *
     * @return this screen
     */
    public MessageBoxScreen title(Observable<String> title) {
        bindStringProperty(TITLE_BINDING, "title", title);
        return this;
    }

    /**
     * Sets a static message body.
     *
     * @param body message body
     *
     * @return this screen
     */
    public MessageBoxScreen body(String body) {
        unbind(BODY_BINDING);
        writeStringProperty("body", body);
        return this;
    }

    /**
     * Binds the message body to an observable value.
     *
     * @param body observable body
     *
     * @return this screen
     */
    public MessageBoxScreen body(Observable<String> body) {
        bindStringProperty(BODY_BINDING, "body", body);
        return this;
    }

    /**
     * Adds the primary message box button.
     *
     * @param label button label
     * @param listener click listener
     *
     * @return created button element
     */
    public MessageBoxButtonElement button1(String label, Consumer<Player> listener) {
        return button1(label, "", listener);
    }

    /**
     * Adds the primary message box button with a tooltip.
     *
     * @param label button label
     * @param tooltip button tooltip
     * @param listener click listener
     *
     * @return created button element
     */
    public MessageBoxButtonElement button1(String label, String tooltip, Consumer<Player> listener) {
        var button = new MessageBoxButtonElement("button1", label, tooltip, this);
        button.addListener(listener);
        setProperty(button);
        return button;
    }

    /**
     * Adds the secondary message box button.
     *
     * @param label button label
     * @param listener click listener
     *
     * @return created button element
     */
    public MessageBoxButtonElement button2(String label, Consumer<Player> listener) {
        return button2(label, "", listener);
    }

    /**
     * Adds the secondary message box button with a tooltip.
     *
     * @param label button label
     * @param tooltip button tooltip
     * @param listener click listener
     *
     * @return created button element
     */
    public MessageBoxButtonElement button2(String label, String tooltip, Consumer<Player> listener) {
        var button = new MessageBoxButtonElement("button2", label, tooltip, this);
        button.addListener(listener);
        setProperty(button);
        return button;
    }
}
