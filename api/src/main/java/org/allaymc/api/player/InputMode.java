package org.allaymc.api.player;

/**
 * Represents a player's input mode, as reported by the client.
 *
 * @author zernix2077
 */
public enum InputMode {
    UNDEFINED,
    MOUSE,
    TOUCH,
    GAMEPAD,
    /**
     * @deprecated since 1.21.120
     */
    @Deprecated
    MOTION_CONTROLLER;

    /**
     * Get input mode by id.
     *
     * @param id the id of the input mode
     * @return the input mode, or {@code null} if the id is unknown
     */
    public static InputMode from(int id) {
        var values = values();
        if (id < 0 || id > values.length) {
            return null;
        }

        return values[id];
    }
}
