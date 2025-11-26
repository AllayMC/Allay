package org.allaymc.api.permission;

/**
 * PermissionCalculator is used to calculate whether a permissible object has a certain permission.
 *
 * @author daoge_cmd
 */
public interface PermissionCalculator {
    /**
     * Calculates the {@link Tristate} state for the given permission.
     * This method is used to determine if a given permission is positively granted,
     * explicitly denied, or undefined for a permissible object.
     *
     * @param permission the {@link Tristate} state of the permission
     */
    Tristate calculatePermission(String permission);
}
