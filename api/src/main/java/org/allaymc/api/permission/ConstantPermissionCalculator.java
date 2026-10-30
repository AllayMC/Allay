package org.allaymc.api.permission;

/**
 * {@code ConstantPermissionCalculator} answers every permission query with one
 * stored {@link Tristate} value. It ignores the permission name, so callers can
 * use one calculator for subjects whose permissions are uniformly granted,
 * denied, or undefined.
 *
 * @param constant the result returned for every query; the record does not
 *                 validate this value, so {@code null} is retained unchanged
 */
public record ConstantPermissionCalculator(Tristate constant) implements PermissionCalculator {
    /**
     * Returns {@code constant} without examining the supplied permission name.
     * Every query receives the same stored value, including {@code null} when
     * the calculator was constructed with that value.
     */
    @Override
    public Tristate calculatePermission(String permission) {
        return this.constant;
    }
}
