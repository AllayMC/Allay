package org.allaymc.api.ddui.internal;

import org.jetbrains.annotations.ApiStatus;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * DDUI object property that stores named child properties.
 *
 * @author xRookieFight
 * @author Miroshka
 */
@ApiStatus.Internal
public class ObjectProperty<T> extends DataDrivenProperty<Map<String, DataDrivenProperty<?>>> {

    public ObjectProperty(String name) {
        this(name, null);
    }

    public ObjectProperty(String name, ObjectProperty<?> parent) {
        super(name, new LinkedHashMap<>(), parent);
    }

    public DataDrivenProperty<?> getProperty(String name) {
        return getValue().get(name);
    }

    public void setProperty(DataDrivenProperty<?> property) {
        var screen = getRootScreen();
        if (screen != null && screen.hasSessions()) {
            throw new IllegalStateException("Cannot mutate DDUI structure after the screen has been attached");
        }

        getValue().put(property.getName(), property);
        if (screen != null) {
            screen.propertyChanged(this);
        }
    }

    @Override
    public Object toWireValue() {
        var children = new LinkedHashMap<String, Object>();
        getValue().forEach((key, property) -> children.put(key, property.toWireValue()));
        return children;
    }
}
