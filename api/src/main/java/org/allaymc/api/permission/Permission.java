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
    private static final PermissionListener COMMAND_PERMISSION_LISTENER = (permissible, value) -> {
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
     * Creates a permission intended for commands.
     * The created permission will be associated with a predefined listener
     * that triggers a command list refresh for players.
     *
     * @param commandName the command name without the leading slash.
     * @param permission  the name of the permission.
     *
     * @return a new permission instance.
     *
     * @throws PermissionException if a permission with the same name already exists.
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
     * Creates a new permission with the given name and description to be used for command.
     * Different from {@link #create(String, String, PermissionListener)}, this method will
     * create a permission with a listener that will resend the available commands to the
     * player when the permission is changed.
     *
     * @param name        the name of the permission.
     * @param description the description of the permission.
     *
     * @return a new permission instance.
     */
    public static Permission createForCommand(String name, String description) {
        return create(name, description, COMMAND_PERMISSION_LISTENER);
    }

    /**
     * Creates a new permission with the given name, description, and optional listener.
     * The created permission is also registered to {@link Registries#PERMISSIONS}.
     *
     * @param name        the name of the permission.
     * @param description the description of the permission.
     * @param listener    the listener of the permission, or {@code null} if no listener is set.
     *
     * @return a new permission instance.
     *
     * @throws PermissionException if a permission with the given name already exists.
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
     * @return the permission name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of this permission.
     *
     * @return the permission description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the listener associated with this permission, if any.
     *
     * @return the permission listener, or {@code null} if none is set.
     */
    public PermissionListener getPermissionListener() {
        return listener;
    }

    @Override
    public String toString() {
        return name;
    }
}
