package org.allaymc.api.permission;

import com.google.common.base.Preconditions;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.registry.Registries;

/**
 * Represents a permission.
 *
 * @author daoge_cmd
 */
public final class Permission {
    private static final PermissionListener DEFAULT_LISTENER = (permissible, value) -> {
        if (permissible instanceof EntityPlayer player) {
            player.requireResendingAvailableCommands();
        }
    };

    private final String name;
    private final String description;
    // Can be null if no listener is set
    private final PermissionListener listener;

    private Permission(String name, String description, PermissionListener listener) {
        this.name = Preconditions.checkNotNull(name);
        this.description = Preconditions.checkNotNull(description);
        this.listener = listener;
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
        return create(name, description, DEFAULT_LISTENER);
    }

    /**
     * Creates a new permission with the given name and description.The created permission
     * will also be registered to {@link Registries#PERMISSIONS}.
     *
     * @param name        the name of the permission.
     * @param description the description of the permission.
     *
     * @return a new permission instance.
     *
     * @throws PermissionException if the name is already exists.
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
     * @param name the name of the permission.
     *
     * @return the permission with the given name, or {@code null} if it does not exist.
     *
     * @throws IllegalArgumentException if the given permission name is invalid.
     */
    public static Permission get(String name) {
        return Registries.PERMISSIONS.get(name);
    }

    /**
     * Gets the name of this permission.
     *
     * @return the name of this permission.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of this permission.
     *
     * @return the description of this permission.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the listener of this permission.
     *
     * @return the listener of this permission, or {@code null} if no listener is set.
     */
    public PermissionListener getPermissionListener() {
        return listener;
    }

    @Override
    public String toString() {
        return name;
    }
}
