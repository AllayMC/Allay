package org.allaymc.api.permission;

import java.util.Collection;

/**
 * {@code Permissible} represents a subject whose permission queries can be
 * delegated to a replaceable {@link PermissionCalculator}. The default query
 * methods preserve individual tristate decisions and require every requested
 * permission to be granted for a bulk check to succeed.
 *
 * @author daoge_cmd
 */
public interface Permissible {

    /**
     * Returns this subject's decision for the supplied permission name.
     * A missing calculator produces {@link Tristate#UNDEFINED}; otherwise the
     * calculator receives the name unchanged and supplies the result directly.
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
     * Returns whether all requested permissions convert to granted decisions.
     * Evaluation stops at the first result whose {@link Tristate#asBoolean()}
     * value is {@code false}; an empty collection returns {@code true}.
     *
     * @param permissions a collection of permission strings to check
     * @return {@code true} if all permissions resolve to {@link Tristate#TRUE};
     * {@code false} otherwise
     */
    default boolean hasPermissions(Collection<String> permissions) {
        return permissions.stream().map(this::hasPermission).allMatch(Tristate::asBoolean);
    }

    /**
     * Returns the calculator currently associated with this subject.
     * A {@code null} result makes the default {@link #hasPermission(String)}
     * implementation return {@link Tristate#UNDEFINED} without invoking any
     * calculator for that permission query.
     *
     * @return the {@code PermissionCalculator} instance currently associated with this object,
     * or {@code null} if no {@code PermissionCalculator} has been assigned (in that case, any
     * permission check will return {@link Tristate#UNDEFINED}).
     */
    PermissionCalculator getPermissionCalculator();

    /**
     * Replaces the calculator used to answer this subject's permission queries.
     * A {@code null} calculator disables delegation in the default
     * {@link #hasPermission(String)} implementation and makes subsequent queries
     * return {@link Tristate#UNDEFINED}.
     *
     * @param calculator the {@link PermissionCalculator} to assign. If set to {@code null},
     *                   any permission check will return {@link Tristate#UNDEFINED}.
     */
    void setPermissionCalculator(PermissionCalculator calculator);

    /**
     * Notifies this subject that its externally calculated permissions changed.
     * Calculator implementations call this hook when dependent state needs
     * refreshing; the default implementation performs no work, while subjects
     * may override it to update client-visible abilities.
     */
    default void onPermissionChange() {
        // The default subject has no permission-dependent state to refresh.
    }
}
