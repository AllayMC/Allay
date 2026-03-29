package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.MessageBoxScreen;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.LongProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;
import org.allaymc.api.player.Player;

import java.util.function.Consumer;

/**
 * DDUI button element used by message box screens.
 *
 * @author Miroshka
 */
public final class MessageBoxButtonElement extends Element {

    private static final String TOOLTIP_BINDING = "binding:tooltip";

    public MessageBoxButtonElement(String name, String label, String toolTip, ObjectProperty<?> parent) {
        super(name, parent);
        setLabel(label);
        if (!toolTip.isEmpty()) {
            setToolTip(toolTip);
        }
        var clickProperty = new LongProperty("onClick", 0L, this);
        clickProperty.addListener(this::triggerListeners);
        setProperty(clickProperty);
    }

    public String getToolTip() {
        var property = getProperty("tooltip");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public MessageBoxButtonElement setToolTip(String toolTip) {
        unbind(TOOLTIP_BINDING);
        writeStringProperty("tooltip", toolTip);
        return this;
    }

    public MessageBoxButtonElement setToolTip(Observable<String> toolTip) {
        bindStringProperty(TOOLTIP_BINDING, "tooltip", toolTip);
        return this;
    }

    public void addListener(Consumer<Player> listener) {
        addListener((player, data) -> listener.accept(player));
    }

    public MessageBoxScreen screen() {
        var screen = getRootScreen();
        if (screen instanceof MessageBoxScreen messageBoxScreen) {
            return messageBoxScreen;
        }

        throw new IllegalStateException("Element is not attached to a message box screen");
    }
}
