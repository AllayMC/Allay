package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BooleanProperty;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.allaymc.api.ddui.internal.StringProperty;

/**
 * Base DDUI element that exposes common label, visibility and disabled state bindings.
 *
 * @author Miroshka
 * @author xRookieFight
 */
public abstract class Element extends ObjectProperty<Object> {

    protected Element(String name, ObjectProperty<?> parent) {
        super(name, parent);
    }

    public String getLabel() {
        var property = getProperty("label");
        return property instanceof StringProperty stringProperty ? stringProperty.getValue() : "";
    }

    public Element setLabel(String label) {
        var property = getProperty("label");
        if (property instanceof StringProperty stringProperty) {
            stringProperty.setValue(label);
            return this;
        }

        setProperty(new StringProperty("label", label, this));
        return this;
    }

    public Element setLabel(Observable<String> label) {
        var property = getProperty("label");
        var stringProperty = property instanceof StringProperty existing
                ? existing
                : new StringProperty("label", label.getValue(), this);
        stringProperty.setValue(label.getValue());
        label.subscribe(value -> {
            if (!Observable.bindingsSuppressed()) {
                stringProperty.setValue(value);
            }
        });
        setProperty(stringProperty);
        return this;
    }

    public boolean isDisabled() {
        var property = getProperty("disabled");
        return property instanceof BooleanProperty booleanProperty && booleanProperty.getValue();
    }

    public Element setDisabled(boolean disabled) {
        var property = getProperty("disabled");
        if (property instanceof BooleanProperty booleanProperty) {
            booleanProperty.setValue(disabled);
            return this;
        }

        setProperty(new BooleanProperty("disabled", disabled, this));
        return this;
    }

    public Element setDisabled(Observable<Boolean> disabled) {
        var property = getProperty("disabled");
        var booleanProperty = property instanceof BooleanProperty existing
                ? existing
                : new BooleanProperty("disabled", disabled.getValue(), this);
        booleanProperty.setValue(disabled.getValue());
        disabled.subscribe(value -> {
            if (!Observable.bindingsSuppressed()) {
                booleanProperty.setValue(value);
            }
        });
        setProperty(booleanProperty);
        return this;
    }

    public boolean isVisible() {
        var property = getProperty("visible");
        return !(property instanceof BooleanProperty booleanProperty) || booleanProperty.getValue();
    }

    public Element setVisibility(boolean visible) {
        var property = getProperty("visible");
        if (property instanceof BooleanProperty booleanProperty) {
            booleanProperty.setValue(visible);
            return this;
        }

        setProperty(new BooleanProperty("visible", visible, this));
        return this;
    }

    public Element setVisibility(Observable<Boolean> visible) {
        var property = getProperty("visible");
        var booleanProperty = property instanceof BooleanProperty existing
                ? existing
                : new BooleanProperty("visible", visible.getValue(), this);
        booleanProperty.setValue(visible.getValue());
        visible.subscribe(value -> {
            if (!Observable.bindingsSuppressed()) {
                booleanProperty.setValue(value);
            }
        });
        setProperty(booleanProperty);
        return this;
    }
}
