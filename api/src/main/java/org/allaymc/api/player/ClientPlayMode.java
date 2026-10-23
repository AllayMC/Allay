package org.allaymc.api.player;

/**
 * {@code ClientPlayMode} identifies the play mode reported in client input packets.
 * These constants mirror protocol categories rather than server game modes; the
 * decoder selects them from the client's reported value, including legacy
 * virtual-reality modes retained for compatibility.
 *
 * @author zernix2077
 */
public enum ClientPlayMode {
    /**
     * Ordinary client play mode.
     */
    NORMAL,
    /**
     * Protocol teaser play mode.
     */
    TEASER,
    /**
     * Protocol screen play mode.
     */
    SCREEN,
    /**
     * Legacy viewer play mode.
     *
     * @deprecated since 1.21.120
     */
    @Deprecated
    VIEWER,
    /**
     * Legacy reality play mode.
     *
     * @deprecated since 1.21.120
     */
    @Deprecated
    REALITY,
    /**
     * Legacy placement play mode.
     *
     * @deprecated since 1.21.120
     */
    @Deprecated
    PLACEMENT,
    /**
     * Legacy living-room play mode.
     *
     * @deprecated since 1.21.120
     */
    @Deprecated
    LIVING_ROOM,
    /**
     * Protocol exit-level play mode.
     */
    EXIT_LEVEL,
    /**
     * Legacy living-room exit mode.
     *
     * @deprecated since 1.21.120
     */
    @Deprecated
    EXIT_LEVEL_LIVING_ROOM
}
