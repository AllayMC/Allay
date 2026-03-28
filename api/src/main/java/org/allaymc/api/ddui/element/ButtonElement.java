package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BooleanProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;
import org.allaymc.api.player.Player;

import java.util.function.Consumer;

/**
 * DDUI button element for custom form screens.
 *
 * @author Miroshka
 */
public class ButtonElement extends CustomFormElement {

    public ButtonElement(String label, ObjectProperty<?> parent) {
        super("button", parent);
        setLabel(label);
        setToolTip("");
        setVisibility(true);
        setDisabled(false);
        var clickProperty = new ButtonClickProperty(this);
        clickProperty.addListener(this::triggerListeners);
        setProperty(clickProperty);
    }

    public String getToolTip() {
        var property = getProperty("tooltip");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public ButtonElement setToolTip(String toolTip) {
        var property = getProperty("tooltip");
        if (property instanceof StringProperty stringProperty) {
            stringProperty.setValue(toolTip);
            return this;
        }

        setProperty(new StringProperty("tooltip", toolTip, this));
        return this;
    }

    public ButtonElement setToolTip(Observable<String> toolTip) {
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

    @Override
    public ButtonElement setVisibility(boolean visible) {
        super.setVisibility(visible);
        var property = getProperty("button_visible");
        if (property instanceof BooleanProperty booleanProperty) {
            booleanProperty.setValue(visible);
            return this;
        }

        setProperty(new BooleanProperty("button_visible", visible, this));
        return this;
    }

    @Override
    public ButtonElement setVisibility(Observable<Boolean> visible) {
        super.setVisibility(visible);
        var property = getProperty("button_visible");
        var booleanProperty = property instanceof BooleanProperty existing
                ? existing
                : new BooleanProperty("button_visible", visible.getValue(), this);
        booleanProperty.setValue(visible.getValue());
        visible.subscribe(value -> {
            if (!Observable.bindingsSuppressed()) {
                booleanProperty.setValue(value);
            }
        });
        setProperty(booleanProperty);
        return this;
    }

    public void addListener(Consumer<Player> listener) {
        addListener((player, data) -> listener.accept(player));
    }
}
