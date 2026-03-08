package org.allaymc.api.player;

/**
 * ClientPlayMode represents player's current play mode, as reported from the input.
 *
 * @author zernix2077
 */
public enum ClientPlayMode {
    NORMAL,
    TEASER,
    SCREEN,
    /**
     * @deprecated since 1.21.120
     */
    VIEWER,
    /**
     * @deprecated since 1.21.120
     */
    REALITY,
    /**
     * @deprecated since 1.21.120
     */
    PLACEMENT,
    /**
     * @deprecated since 1.21.120
     */
    LIVING_ROOM,
    EXIT_LEVEL,
    /**
     * @deprecated since 1.21.120
     */
    EXIT_LEVEL_LIVING_ROOM
}
