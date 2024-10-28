package org.allaymc.api.permission;

import org.allaymc.api.permission.tree.PermissionTree;

import java.util.Collection;

/**
 * Represents a permissible object.
 *
 * @author daoge_cmd
 */
public interface Permissible {
    /**
     * Check if this object is an operator.
     *
     * @return {@code true} if this object is an operator, {@code false} otherwise.
     */
    default boolean isOp() {
        return getPermissionTree().isOp();
    }

    /**
     * Set if this object is an operator.
     *
     * @param value {@code true} if this object is an operator, {@code false} otherwise.
     */
    default void setOp(boolean value) {
        getPermissionTree().setOp(value);
    }

    /**
     * Check if this object has the given permission.
     *
     * @param permission the permission to check.
     *
     * @return {@code true} if this object has the given permission, {@code false} otherwise.
     */
    default boolean hasPermission(String permission) {
        return getPermissionTree().hasPermission(permission);
    }

    /**
     * Check if this object has all the given permissions.
     *
     * @param permissions the permissions to check.
     *
     * @return {@code true} if this object has all the given permissions, {@code false} otherwise.
     */
    default boolean hasPermission(Collection<String> permissions) {
        return permissions.stream().allMatch(this::hasPermission);
    }

    /**
     * Add a permission to this object.
     *
     * @param permission the permission to add.
     *
     * @return this object.
     */
    default Permissible addPermission(String permission) {
        getPermissionTree().addPermission(permission);
        return this;
    }

    /**
     * Remove a permission from this object.
     *
     * @param permission the permission to remove.
     *
     * @return this object.
     */
    default Permissible removePermission(String permission) {
        getPermissionTree().removePermission(permission);
        return this;
    }

    /**
     * Set the value of a permission.
     *
     * @param permission  the permission to set.
     * @param value {@code true} to add the permission, {@code false} to remove it.
     *
     * @return this object.
     */
    default Permissible setPermission(String permission, boolean value) {
        if (value) addPermission(permission);
        else removePermission(permission);
        return this;
    }

    /**
     * Get the permission tree of this object.
     *
     * @return the permission tree of this object.
     */
    PermissionTree getPermissionTree();
}
