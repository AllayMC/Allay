package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BooleanProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.player.Player;

import java.util.function.Consumer;

/**
 * DDUI close button element bound to a screen close action.
 *
 * @author Miroshka
 */
public final class CloseButtonElement extends Element {

    public CloseButtonElement(ObjectProperty<?> parent) {
        super("closeButton", parent);
        setLabel("Close");
        setVisibility(true);
        var clickProperty = new ButtonClickProperty(this);
        clickProperty.addListener(this::triggerListeners);
        setProperty(clickProperty);
    }

    @Override
    public CloseButtonElement setVisibility(boolean visible) {
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
    public CloseButtonElement setVisibility(Observable<Boolean> visible) {
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
