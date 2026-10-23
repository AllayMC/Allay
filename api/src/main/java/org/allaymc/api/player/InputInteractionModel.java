package org.allaymc.api.player;

/**
 * {@code InputInteractionModel} identifies the interaction layout reported by client
 * input packets. These constants distinguish touch, crosshair, and classic
 * interaction styles; they describe client input presentation and do not grant
 * server abilities or select a player's game mode.
 *
 * @author zernix2077
 */
public enum InputInteractionModel {
    /**
     * Touch-based interaction layout.
     */
    TOUCH,
    /**
     * Crosshair-based interaction layout.
     */
    CROSSHAIR,
    /**
     * Classic client interaction layout.
     */
    CLASSIC
}
