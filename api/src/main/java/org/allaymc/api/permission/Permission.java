package org.allaymc.api.permission;

import com.google.common.base.Preconditions;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.registry.Registries;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a permission.
 *
 * @author daoge_cmd
 */
public final class Permission {
    private static final PermissionListener COMMAND_PERMISSION_LISTENER = (permissible, value) -> {
        if (permissible instanceof EntityPlayer player) {
            player.sendCommands();
        }
    };

    private final String name;
    private final String description;
    private final Set<PermissionListener> listeners;

    private Permission(String name, String description, PermissionListener listener) {
        this.name = Preconditions.checkNotNull(name);
        this.description = Preconditions.checkNotNull(description);
        this.listeners = new HashSet<>();
        if (listener != null) {
            this.listeners.add(listener);
        }
    }

    /**
     * Creates a permission intended for commands. The created permission will be associated with a
     * predefined listener that triggers a command list refresh for players.
     *
     * @param commandName the command name without the leading slash
     * @param permission  the name of the permission
     * @return a new permission instance
     * @throws PermissionException if a permission with the same name already exists
     */
    public static Permission createForCommand(String commandName, String permission) {
        return create(permission, "The permission to use the command /" + commandName, COMMAND_PERMISSION_LISTENER);
    }

    /**
     * @see #create(String, String, PermissionListener)
     */
    public static Permission create(String name) {
        return create(name, "");
    }

    /**
     * @see #create(String, String, PermissionListener)
     */
    public static Permission create(String name, String description) {
        return create(name, description, null);
    }

    /**
     * Creates a new permission with the given name, description, and optional listener.
     * The created permission is also registered to {@link Registries#PERMISSIONS}.
     *
     * @param name        the name of the permission
     * @param description the description of the permission
     * @param listener    the listener of the permission, or {@code null} if no listener is set
     * @return a new permission instance
     * @throws PermissionException if a permission with the given name already exists
     */
    public static Permission create(String name, String description, PermissionListener listener) {
        var permission = new Permission(name, description, listener);
        if (Registries.PERMISSIONS.get(name) != null) {
            throw new PermissionException("Permission with name '" + name + "' already exists");
        }

        Registries.PERMISSIONS.register(name, permission);
        return permission;
    }

    /**
     * Gets an existing permission by its name.
     *
     * @param name the name of the permission
     * @return the permission with the given name, or {@code null} if it does not exist
     * @throws IllegalArgumentException if the given permission name is invalid
     */
    public static Permission get(String name) {
        return Registries.PERMISSIONS.get(name);
    }

    /**
     * Gets the name of this permission.
     *
     * @return the permission name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of this permission.
     *
     * @return the permission description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves an unmodifiable set of {@link PermissionListener}s associated with this permission.
     *
     * @return an unmodifiable view of the set of {@link PermissionListener}s associated with this permission
     */
    @UnmodifiableView
    public Set<PermissionListener> getListeners() {
        return Collections.unmodifiableSet(listeners);
    }

    /**
     * Adds a {@link PermissionListener} to this permission. The listener will be triggered when changes
     * are made to permissions associated with permissible entities.
     *
     * @param listener the {@link PermissionListener} to be added; must not be {@code null}
     */
    public void addListener(PermissionListener listener) {
        listeners.add(listener);
    }

    @Override
    public String toString() {
        return name;
    }
}
