package org.allaymc.api.permission;

/**
 * A permission calculator that always returns a constant {@link Tristate} value.
 *
 * @param constant the constant {@link Tristate} value that this calculator
 *                 will always return for permission checks.
 */
public record ConstantPermissionCalculator(Tristate constant) implements PermissionCalculator {
    @Override
    public Tristate calculatePermission(String permission) {
        return this.constant;
    }
}
