package org.allaymc.api.player;

/**
 * {@code InputMode} identifies the input device category reported by the client.
 * Login and input decoding use these constants as client metadata; {@code from}
 * returns {@code null} for unsupported numeric values rather than substituting
 * {@code UNDEFINED}.
 *
 * @author zernix2077
 */
public enum InputMode {
    /**
     * Unspecified client input category.
     */
    UNDEFINED,
    /**
     * Mouse-based client input.
     */
    MOUSE,
    /**
     * Touchscreen client input.
     */
    TOUCH,
    /**
     * Gamepad client input.
     */
    GAMEPAD,
    /**
     * Legacy motion-controller client input.
     *
     * @deprecated since 1.21.120
     */
    @Deprecated
    MOTION_CONTROLLER;

    /**
     * Returns the input category at the supplied protocol index. Negative indexes
     * and indexes beyond the declared constants return {@code null}, allowing
     * callers to detect unsupported client input values.
     */
    public static InputMode from(int id) {
        var values = values();
        if (id < 0 || id >= values.length) {
            return null;
        }

        return values[id];
    }
}
