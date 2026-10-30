package org.allaymc.api.player;

/**
 * {@code CameraShakeType} identifies whether a camera shake changes position or
 * rotation. {@code Player.shakeCamera} passes this selection to the client; these
 * constants describe presentation behavior and do not change the player entity's
 * server-side location or rotation.
 *
 * @author daoge_cmd
 */
public enum CameraShakeType {
    /**
     * Shakes the camera by adjusting its position.
     */
    POSITIONAL,
    /**
     * Shakes the camera by adjusting its rotation.
     */
    ROTATIONAL
}
