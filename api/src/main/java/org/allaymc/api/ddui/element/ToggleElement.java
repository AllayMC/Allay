package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.BindingScope;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BooleanProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;
import org.allaymc.api.player.Player;

/**
 * DDUI toggle element for boolean input.
 *
 * @author Miroshka
 */
public final class ToggleElement extends CustomFormElement {

    private static final String TOGGLED_BINDING = "binding:toggled";
    private static final String DESCRIPTION_BINDING = "binding:description";
    private Observable<Boolean> toggledBinding;

    public ToggleElement(String label, Observable<Boolean> toggled, ObjectProperty<?> parent) {
        super("toggle", parent);
        setLabel(label);
        setVisibility(true);
        setDisabled(false);
        setDescription("");
        setToggled(toggled);
    }

    public boolean isToggled() {
        var property = getProperty("toggled");
        return property instanceof BooleanProperty booleanProperty && booleanProperty.getValue();
    }

    public ToggleElement setToggled(boolean toggled) {
        unbind(TOGGLED_BINDING);
        toggledBinding = null;
        toggledProperty().setValue(toggled);
        return this;
    }

    public ToggleElement setToggled(Observable<Boolean> toggled) {
        var booleanProperty = toggledProperty();
        toggledBinding = toggled;
        booleanProperty.setValue(toggled.getValue());
        bind(TOGGLED_BINDING, toggled, booleanProperty::setValue);
        return this;
    }

    public String getDescription() {
        var property = getProperty("description");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public ToggleElement setDescription(String description) {
        unbind(DESCRIPTION_BINDING);
        writeStringProperty("description", description);
        return this;
    }

    @Override
    protected void applyVisibility(boolean visible) {
        super.applyVisibility(visible);
        writeBooleanProperty("toggle_visible", visible);
    }

    @Override
    public void triggerListeners(Player player, Object data) {
        super.triggerListeners(player, data);
        if (data instanceof Boolean bool) {
            var currentBinding = toggledBinding;
            if (currentBinding != null) {
                BindingScope.suppressed(() -> currentBinding.setValue(bool));
            }
        }
    }

    private BooleanProperty toggledProperty() {
        return ensureProperty("toggled", () -> {
            var property = new BooleanProperty("toggled", false, this);
            property.addListener(this::triggerListeners);
            return property;
        });
    }
}
