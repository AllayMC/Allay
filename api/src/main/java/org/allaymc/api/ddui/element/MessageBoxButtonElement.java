package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.MessageBoxScreen;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;
import org.allaymc.api.player.Player;

import java.util.function.Consumer;

/**
 * DDUI button element used by message box screens.
 *
 * @author Miroshka
 * @author xRookieFight
 */
public final class MessageBoxButtonElement extends Element {

    public MessageBoxButtonElement(String name, String label, String toolTip, ObjectProperty<?> parent) {
        super(name, parent);
        setLabel(label);
        if (!toolTip.isEmpty()) {
            setToolTip(toolTip);
        }
        var clickProperty = new ButtonClickProperty(this);
        clickProperty.addListener(this::triggerListeners);
        setProperty(clickProperty);
    }

    public String getToolTip() {
        var property = getProperty("tooltip");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public MessageBoxButtonElement setToolTip(String toolTip) {
        var property = getProperty("tooltip");
        if (property instanceof StringProperty stringProperty) {
            stringProperty.setValue(toolTip);
            return this;
        }

        setProperty(new StringProperty("tooltip", toolTip, this));
        return this;
    }

    public MessageBoxButtonElement setToolTip(Observable<String> toolTip) {
        var property = getProperty("tooltip");
        var stringProperty = property instanceof StringProperty existing
                ? existing
                : new StringProperty("tooltip", toolTip.getValue(), this);
        stringProperty.setValue(toolTip.getValue());
        toolTip.subscribe(value -> {
            if (!Observable.bindingsSuppressed()) {
                stringProperty.setValue(value);
            }
        });
        setProperty(stringProperty);
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
