package org.allaymc.api.ddui.internal;

import org.allaymc.api.ddui.DataDrivenScreen;
import org.allaymc.api.player.Player;
import org.jetbrains.annotations.ApiStatus;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * Base DDUI property abstraction used for serialization, path resolution and input dispatch.
 *
 * @param <T> property value type
 *
 * @author xRookieFight
 * @author Miroshka
 */
@ApiStatus.Internal
public abstract class DataDrivenProperty<T> {

    private final ObjectProperty<?> parent;
    private final Set<BiConsumer<Player, Object>> listeners = new LinkedHashSet<>();

    private long triggerCount;
    private String name;
    private T value;

    protected DataDrivenProperty(String name, T value, ObjectProperty<?> parent) {
        this.name = name;
        this.value = value;
        this.parent = parent;
    }

    public ObjectProperty<?> getParent() {
        return parent;
    }

    public long getTriggerCount() {
        return triggerCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        listeners.add(listener);
    }

    public void removeListener(BiConsumer<Player, Object> listener) {
        listeners.remove(listener);
    }

    public void triggerListeners(Player player, Object data) {
        triggerCount++;
        listeners.forEach(listener -> listener.accept(player, data));
    }

    public String getPath() {
        if (parent == null) {
            return name;
        }

        var parentPath = parent.getPath();
        if (parent.getName().isEmpty()) {
            return name;
        }

        try {
            Integer.parseInt(name);
            return parentPath + "[" + name + "]";
        } catch (NumberFormatException ignored) {
            return parentPath + "." + name;
        }
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

    public abstract Object toWireValue();
}
