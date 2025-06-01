package org.allaymc.api.permission;

/**
 * @author daoge_cmd
 */
@FunctionalInterface
public interface PermissionListener {
    /**
     * Called when a permission value changes.
     *
     * @param permissible the permissible entity that the target permission group is belong to.
     *                    Can be {@code null} if the permission group is not belong to any entity.
     * @param value       the new value of the permission.
     *
     * @see PermissionGroup#setPermission(Permission, boolean, Permissible)
     */
    void onChange(Permissible permissible, boolean value);
}
