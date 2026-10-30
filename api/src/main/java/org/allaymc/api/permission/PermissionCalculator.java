package org.allaymc.api.permission;

/**
 * {@code PermissionCalculator} supplies permission decisions for a subject
 * represented by its implementation. Each query distinguishes a grant, an
 * explicit denial, and an undefined permission, allowing {@link Permissible}
 * consumers to retain that distinction before converting decisions to booleans.
 *
 * @author daoge_cmd
 */
public interface PermissionCalculator {
    /**
     * Calculates the permission decision for the implementation's subject.
     * Implementations return {@link Tristate#TRUE} for a grant,
     * {@link Tristate#FALSE} for an explicit denial, or
     * {@link Tristate#UNDEFINED} when they supply no decision.
     *
     * @param permission the permission name whose decision is requested
     */
    Tristate calculatePermission(String permission);
}
