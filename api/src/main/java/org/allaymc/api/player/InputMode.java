package org.allaymc.api.player;

/**
 * InputMode represents player's current input mode, as reported from the input.
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
    MOTION_CONTROLLER
}
