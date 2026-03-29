package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.LongProperty;
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
        var clickProperty = new LongProperty("onClick", 0L, this);
        clickProperty.addListener(this::triggerListeners);
        setProperty(clickProperty);
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
