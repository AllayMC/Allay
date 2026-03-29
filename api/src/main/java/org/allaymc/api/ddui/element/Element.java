package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BooleanProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;

/**
 * Base DDUI element that exposes common label, visibility and disabled state bindings.
 *
 * @author Miroshka
 */
public abstract class Element extends ObjectProperty<Object> {

    private static final String LABEL_BINDING = "binding:label";
    private static final String DISABLED_BINDING = "binding:disabled";
    private static final String VISIBILITY_BINDING = "binding:visibility";

    protected Element(String name, ObjectProperty<?> parent) {
        super(name, parent);
    }

    public String getLabel() {
        var property = getProperty("label");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public Element setLabel(String label) {
        unbind(LABEL_BINDING);
        writeStringProperty("label", label);
        return this;
    }

    public Element setLabel(Observable<String> label) {
        bindStringProperty(LABEL_BINDING, "label", label);
        return this;
    }

    public boolean isDisabled() {
        var property = getProperty("disabled");
        return property instanceof BooleanProperty booleanProperty && booleanProperty.getValue();
    }

    public Element setDisabled(boolean disabled) {
        unbind(DISABLED_BINDING);
        writeBooleanProperty("disabled", disabled);
        return this;
    }

    public Element setDisabled(Observable<Boolean> disabled) {
        bindBooleanProperty(DISABLED_BINDING, "disabled", disabled);
        return this;
    }

    public boolean isVisible() {
        var property = getProperty("visible");
        return !(property instanceof BooleanProperty booleanProperty) || booleanProperty.getValue();
    }

    public Element setVisibility(boolean visible) {
        unbind(VISIBILITY_BINDING);
        applyVisibility(visible);
        return this;
    }

    public Element setVisibility(Observable<Boolean> visible) {
        applyVisibility(visible.getValue());
        bind(VISIBILITY_BINDING, visible, this::applyVisibility);
        return this;
    }

    protected void applyVisibility(boolean visible) {
        writeBooleanProperty("visible", visible);
    }
}
