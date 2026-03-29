package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.LongProperty;
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

    private static final String TOOLTIP_BINDING = "binding:tooltip";

    public ButtonElement(String label, ObjectProperty<?> parent) {
        super("button", parent);
        setLabel(label);
        setToolTip("");
        setVisibility(true);
        setDisabled(false);
        var clickProperty = new LongProperty("onClick", 0L, this);
        clickProperty.addListener(this::triggerListeners);
        setProperty(clickProperty);
    }

    public String getToolTip() {
        var property = getProperty("tooltip");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public ButtonElement setToolTip(String toolTip) {
        unbind(TOOLTIP_BINDING);
        writeStringProperty("tooltip", toolTip);
        return this;
    }

    public ButtonElement setToolTip(Observable<String> toolTip) {
        bindStringProperty(TOOLTIP_BINDING, "tooltip", toolTip);
        return this;
    }

    @Override
    protected void applyVisibility(boolean visible) {
        super.applyVisibility(visible);
        writeBooleanProperty("button_visible", visible);
    }

    public void addListener(Consumer<Player> listener) {
        addListener((player, data) -> listener.accept(player));
    }
}
