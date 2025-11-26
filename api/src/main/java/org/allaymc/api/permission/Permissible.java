package org.allaymc.api.permission;

import java.util.Collection;

/**
 * Represents an object that can have permissions.
 *
 * @author daoge_cmd
 */
public interface Permissible {

    /**
     * Checks if this permissible object has a certain permission. This is simply done by
     * calling {@link PermissionCalculator#calculatePermission(String)}.
     *
     * @param permission the permission to check
     * @return the {@link Tristate} state of the permission
     */
    default Tristate hasPermission(String permission) {
        var calculator = getPermissionCalculator();
        if (calculator == null) {
            return Tristate.UNDEFINED;
        }

        return calculator.calculatePermission(permission);
    }

    /**
     * Checks if this permissible object has all the specified permissions.
     * Each permission is evaluated, and the method returns {@code true}
     * only if all permissions resolve to {@link Tristate#TRUE}.
     *
     * @param permissions a collection of permission strings to check
     * @return {@code true} if all permissions resolve to {@link Tristate#TRUE};
     * {@code false} otherwise
     */
    default boolean hasPermissions(Collection<String> permissions) {
        return permissions.stream().map(this::hasPermission).allMatch(Tristate::asBoolean);
    }

    /**
     * Retrieves the {@code PermissionCalculator} associated with this permissible object.
     * The {@code PermissionCalculator} is responsible for determining whether a certain
     * permission is granted to a subject.
     *
     * @return the {@code PermissionCalculator} instance currently associated with this object,
     * or {@code null} if no {@code PermissionCalculator} has been assigned (in that case, any
     * permission check will return {@link Tristate#UNDEFINED}).
     */
    PermissionCalculator getPermissionCalculator();

    /**
     * Sets the {@link PermissionCalculator} instance to be used for calculating permissions
     * associated with this object.
     *
     * @param calculator the {@link PermissionCalculator} to assign. If set to {@code null},
     *                   any permission check will return {@link Tristate#UNDEFINED}.
     */
    void setPermissionCalculator(PermissionCalculator calculator);

    /**
     * Called when the permissions associated with this permissible object change. This method
     * is used to update the state of the permissible object if any permission changes.
     * <p>
     * For example, if a player's "flight permission" changes, the server should send an update
     * to the client to enable the player to fly.
     * <p>
     * This method is expected to be called by the implementation of the {@link PermissionCalculator},
     * since the permissible itself won't know if any permissions have changed.
     */
    default void onPermissionChange() {
        // no-op
    }
}
