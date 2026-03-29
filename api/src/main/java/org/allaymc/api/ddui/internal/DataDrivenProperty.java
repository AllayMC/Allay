package org.allaymc.api.ddui.internal;

import org.allaymc.api.ddui.DataDrivenScreen;
import org.allaymc.api.player.Player;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Base DDUI property abstraction used for serialization, path resolution and input dispatch.
 *
 * @param <T> property value type
 *
 * @author Miroshka
 */
@ApiStatus.Internal
public abstract class DataDrivenProperty<T> {

    private final ObjectProperty<?> parent;
    private final Map<Object, BiConsumer<Player, Object>> listeners = new LinkedHashMap<>();

    private String name;
    private T value;
    private String cachedPath;

    protected DataDrivenProperty(String name, T value, ObjectProperty<?> parent) {
        this.name = name;
        this.value = value;
        this.parent = parent;
    }

    public ObjectProperty<?> getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.cachedPath = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        if (Objects.equals(this.value, value)) {
            return;
        }

        this.value = value;
        var screen = getRootScreen();
        if (screen != null) {
            screen.propertyChanged(this);
        }
    }

    public void addListener(BiConsumer<Player, Object> listener) {
        listeners.put(listener, listener);
    }

    public void removeListener(BiConsumer<Player, Object> listener) {
        listeners.remove(listener);
    }

    public void triggerListeners(Player player, Object data) {
        listeners.values().forEach(listener -> listener.accept(player, data));
    }

    public String getPath() {
        if (cachedPath != null) {
            return cachedPath;
        }

        if (parent == null) {
            cachedPath = name;
            return cachedPath;
        }

        var segments = new ArrayDeque<String>();
        DataDrivenProperty<?> current = this;
        while (current != null && current.getParent() != null && !current.getParent().getName().isEmpty()) {
            segments.addFirst(current.getName());
            current = current.getParent();
        }
        if (current != null) {
            segments.addFirst(current.getName());
        }

        var path = new StringBuilder();
        var first = true;
        for (var segment : segments) {
            appendPathSegment(path, segment, first);
            first = false;
        }
        cachedPath = path.toString();
        return cachedPath;
    }

    public DataDrivenScreen getRootScreen() {
        if (parent != null) {
            return parent.getRootScreen();
        }

        return null;
    }

    public void acceptInput(Player player, Object data) {
        triggerListeners(player, data);
    }

    public boolean requiresFullRefresh() {
        return false;
    }

    public abstract Object toWireValue();

    private static void appendPathSegment(StringBuilder path, String segment, boolean first) {
        if (first) {
            path.append(segment);
            return;
        }
        if (isNumeric(segment)) {
            path.append('[').append(segment).append(']');
            return;
        }
        path.append('.').append(segment);
    }

    private static boolean isNumeric(String value) {
        if (value.isEmpty()) {
            return false;
        }
        for (var index = 0; index < value.length(); index++) {
            if (!Character.isDigit(value.charAt(index))) {
                return false;
            }
        }
        return true;
    }
}
