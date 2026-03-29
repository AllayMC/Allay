package org.allaymc.api.ddui.internal;

import org.allaymc.api.ddui.Observable;
import org.jetbrains.annotations.ApiStatus;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * DDUI object property that stores named child properties.
 *
 * @author Miroshka
 */
@ApiStatus.Internal
public class ObjectProperty<T> extends DataDrivenProperty<Map<String, DataDrivenProperty<?>>> {

    private final Map<String, Observable.Subscription> bindings = new LinkedHashMap<>();

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

        var previous = getValue().put(property.getName(), property);
        if (previous != null && previous != property && previous instanceof ObjectProperty<?> objectProperty) {
            objectProperty.disposeBindings();
        }
        if (screen != null) {
            screen.structureChanged();
        }
    }

    @SuppressWarnings("unchecked")
    protected final <P extends DataDrivenProperty<?>> P ensureProperty(String name, Supplier<P> factory) {
        var existing = getProperty(name);
        if (existing != null) {
            return (P) existing;
        }

        var created = factory.get();
        setProperty(created);
        return created;
    }

    protected final <V> void bind(String key, Observable<V> source, Consumer<V> listener) {
        unbind(key);
        bindings.put(key, source.bind(listener));
    }

    protected final void unbind(String key) {
        var binding = bindings.remove(key);
        if (binding != null) {
            binding.cancel();
        }
    }

    protected final StringProperty writeStringProperty(String propertyName, String value) {
        var property = ensureProperty(propertyName, () -> new StringProperty(propertyName, value, this));
        property.setValue(value);
        return property;
    }

    protected final StringProperty bindStringProperty(String bindingKey, String propertyName, Observable<String> source) {
        var property = writeStringProperty(propertyName, source.getValue());
        bind(bindingKey, source, property::setValue);
        return property;
    }

    protected final DisplayTextProperty writeDisplayTextProperty(String propertyName, String value) {
        var property = ensureProperty(propertyName, () -> new DisplayTextProperty(propertyName, value, this));
        property.setValue(value);
        return property;
    }

    protected final DisplayTextProperty bindDisplayTextProperty(String bindingKey, String propertyName, Observable<String> source) {
        var property = writeDisplayTextProperty(propertyName, source.getValue());
        bind(bindingKey, source, property::setValue);
        return property;
    }

    protected final BooleanProperty writeBooleanProperty(String propertyName, boolean value) {
        var property = ensureProperty(propertyName, () -> new BooleanProperty(propertyName, value, this));
        property.setValue(value);
        return property;
    }

    protected final BooleanProperty bindBooleanProperty(String bindingKey, String propertyName, Observable<Boolean> source) {
        var property = writeBooleanProperty(propertyName, source.getValue());
        bind(bindingKey, source, property::setValue);
        return property;
    }

    protected final LongProperty writeLongProperty(String propertyName, long value) {
        var property = ensureProperty(propertyName, () -> new LongProperty(propertyName, value, this));
        property.setValue(value);
        return property;
    }

    protected final LongProperty bindLongProperty(String bindingKey, String propertyName, Observable<Long> source) {
        var property = writeLongProperty(propertyName, source.getValue());
        bind(bindingKey, source, property::setValue);
        return property;
    }

    public void disposeBindings() {
        bindings.values().forEach(Observable.Subscription::cancel);
        bindings.clear();
        getValue().values().forEach(property -> {
            if (property instanceof ObjectProperty<?> objectProperty) {
                objectProperty.disposeBindings();
            }
        });
    }

    @Override
    public Object toWireValue() {
        var children = new LinkedHashMap<String, Object>();
        getValue().forEach((key, property) -> children.put(key, property.toWireValue()));
        return children;
    }
}
