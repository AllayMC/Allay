package org.allaymc.api.dialog;

import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * {@code ModelSettings} represents the scale, offset, and rotation used to display an entity in a
 * dialog. Rotation components use degrees for yaw, whole-body pitch, and whole-body roll.
 * Construction retains the vector references rather than copying mutable vector implementations.
 *
 * @param scale entity model scale vector
 * @param offset entity model offset vector
 * @param rotation yaw, whole-body pitch, and whole-body roll in degrees
 *
 * @author daoge_cmd | df-mc
 */
public record ModelSettings(Vector3dc scale, Vector3dc offset, Vector3dc rotation) {
    /**
     * {@code DEFAULT} supplies unit scale with zero offset and rotation. Its vectors are retained
     * mutable instances, so callers must avoid modifying them through casts.
     */
    public static final ModelSettings DEFAULT = new ModelSettings(
            new Vector3d(1, 1, 1),
            new Vector3d(),
            new Vector3d()
    );
}
