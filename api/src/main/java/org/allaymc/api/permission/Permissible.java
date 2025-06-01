package org.allaymc.api.permission;

import java.util.Collection;

/**
 * Represents an entity that can have permissions.
 *
 * @author daoge_cmd
 */
public interface Permissible {

    /**
     * Gets the permission group of this entity.
     *
     * @return the permission group of this entity.
     */
    PermissionGroup getPermissionGroup();

    /**
     * Override this method to return the actual permissible object. Useful when you want the
     * {@link PermissionListener} can get the actual permissible object.
     *
     * @return the actual permissible object.
     */
    default Permissible getPermissible() {
        return this;
    }

    /**
     * @see PermissionGroup#hasPermission(Permission)
     */
    default boolean hasPermission(Permission permission) {
        return getPermissionGroup().hasPermission(permission);
    }

    /**
     * @see PermissionGroup#hasPermissions(Permission...)
     */
    default boolean hasPermissions(Permission... permissions) {
        return getPermissionGroup().hasPermissions(permissions);
    }

    /**
     * @see PermissionGroup#hasPermissions(Collection)
     */
    default boolean hasPermissions(Collection<Permission> permissions) {
        return getPermissionGroup().hasPermissions(permissions);
    }

    /**
     * @see PermissionGroup#hasPermissions(PermissionGroup, boolean)
     */
    default boolean hasPermissions(PermissionGroup group, boolean includeParentPermissions) {
        return getPermissionGroup().hasPermissions(group, includeParentPermissions);
    }

    /**
     * @see PermissionGroup#addPermission(Permission, Permissible)
     */
    default Permissible addPermission(Permission permission) {
        getPermissionGroup().addPermission(permission, getPermissible());
        return this;
    }

    /**
     * @see PermissionGroup#removePermission(Permission, Permissible)
     */
    default Permissible removePermission(Permission permission) {
        getPermissionGroup().removePermission(permission, getPermissible());
        return this;
    }

    /**
     * @see PermissionGroup#setPermission(Permission, boolean, Permissible)
     */
    default Permissible setPermission(Permission permission, boolean value) {
        getPermissionGroup().setPermission(permission, value, getPermissible());
        return this;
    }

    /**
     * @see PermissionGroup#isOperator()
     */
    default boolean isOperator() {
        return getPermissionGroup().isOperator();
    }

    /**
     * @see PermissionGroup#setOperator(boolean, Permissible)
     */
    default Permissible setOperator(boolean value) {
        getPermissionGroup().setOperator(value, getPermissible());
        return this;
    }
}
