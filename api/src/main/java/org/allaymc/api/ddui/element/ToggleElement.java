package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BooleanProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;
import org.allaymc.api.player.Player;

/**
 * DDUI toggle element for boolean input.
 *
 * @author xRookieFight
 * @author Miroshka
 */
public final class ToggleElement extends CustomFormElement {

    private final Observable<Boolean> toggled;

    public ToggleElement(String label, Observable<Boolean> toggled, ObjectProperty<?> parent) {
        super("toggle", parent);
        this.toggled = toggled;
        setLabel(label);
        setVisibility(true);
        setDisabled(false);
        setDescription("");
        setToggled(toggled.getValue());
        toggled.subscribe(value -> {
            if (!Observable.bindingsSuppressed()) {
                setToggled(value);
            }
        });
    }

    public boolean isToggled() {
        var property = getProperty("toggled");
        return property instanceof BooleanProperty booleanProperty && booleanProperty.getValue();
    }

    public ToggleElement setToggled(boolean toggled) {
        var property = getProperty("toggled");
        if (property instanceof BooleanProperty booleanProperty) {
            booleanProperty.setValue(toggled);
            return this;
        }

        var booleanProperty = new BooleanProperty("toggled", toggled, this);
        booleanProperty.addListener(this::triggerListeners);
        setProperty(booleanProperty);
        return this;
    }

    public String getDescription() {
        var property = getProperty("description");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public ToggleElement setDescription(String description) {
        var property = getProperty("description");
        if (property instanceof StringProperty stringProperty) {
            stringProperty.setValue(description);
            return this;
        }

        setProperty(new StringProperty("description", description, this));
        return this;
    }

    @Override
    public ToggleElement setVisibility(boolean visible) {
        super.setVisibility(visible);
        var property = getProperty("toggle_visible");
        if (property instanceof BooleanProperty booleanProperty) {
            booleanProperty.setValue(visible);
            return this;
        }

        setProperty(new BooleanProperty("toggle_visible", visible, this));
        return this;
    }

    @Override
    public void triggerListeners(Player player, Object data) {
        super.triggerListeners(player, data);
        if (data instanceof Boolean bool) {
            setToggled(bool);
            Observable.withBindingsSuppressed(() -> toggled.setValue(bool));
        }
    }
}
