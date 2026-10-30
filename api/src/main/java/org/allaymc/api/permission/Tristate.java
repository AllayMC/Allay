package org.allaymc.api.permission;

/**
 * {@code Tristate} represents a granted, explicitly denied, or undefined
 * permission decision. Its three constants preserve the difference between a
 * denial and the absence of a decision, although {@link #asBoolean()} maps both
 * of those cases to {@code false} for authorization checks.
 *
 * @author daoge_cmd
 */
public enum Tristate {

    /**
     * An explicit permission grant.
     */
    TRUE(true),

    /**
     * An explicit permission denial.
     */
    FALSE(false),

    /**
     * An absent permission decision.
     */
    UNDEFINED(false);

    /**
     * Returns the explicit grant or denial represented by {@code val}.
     *
     * @param val the boolean value
     * @return {@link #TRUE} for {@code true}, or {@link #FALSE} for {@code false}
     */
    public static Tristate fromBoolean(boolean val) {
        return val ? TRUE : FALSE;
    }

    /**
     * Returns the decision represented by a nullable boolean value.
     * A {@code null} input yields {@link #UNDEFINED}, preserving the absence of
     * a decision instead of converting it into an explicit denial.
     *
     * @param val the boolean value
     * @return {@link #UNDEFINED} for {@code null}, {@link #TRUE} for
     *         {@code true}, or {@link #FALSE} for {@code false}
     */
    public static Tristate fromNullableBoolean(Boolean val) {
        if (val == null) {
            return UNDEFINED;
        }
        return val ? TRUE : FALSE;
    }

    private final boolean booleanValue; // boolean authorization decision

    /**
     * Creates a decision with its stored boolean authorization result.
     */
    Tristate(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    /**
     * Returns the boolean authorization result for this permission decision.
     * {@link #UNDEFINED} and {@link #FALSE} both return {@code false}, so callers
     * requiring an explicit denial must compare the enum constants instead.
     *
     * @return {@code true} only for {@link #TRUE}
     */
    public boolean asBoolean() {
        return this.booleanValue;
    }
}
