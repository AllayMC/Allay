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
    @Deprecated
    VIEWER,
    /**
     * @deprecated since 1.21.120
     */
    @Deprecated
    REALITY,
    /**
     * @deprecated since 1.21.120
     */
    @Deprecated
    PLACEMENT,
    /**
     * @deprecated since 1.21.120
     */
    @Deprecated
    LIVING_ROOM,
    EXIT_LEVEL,
    /**
     * @deprecated since 1.21.120
     */
    @Deprecated
    EXIT_LEVEL_LIVING_ROOM
}
