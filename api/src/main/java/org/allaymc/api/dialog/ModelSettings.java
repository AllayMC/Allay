package org.allaymc.api.dialog;

import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * ModelSettings holds optional fields that change the way the entity shown in the dialog is displayed.
 *
 * @param scale    the scale of the entity displayed in the dialog.
 * @param offset   the offset of the entity shown in the dialog.
 * @param rotation the rotation of the entity shown in the dialog. This rotation functions a bit differently
 *                 to the normal entity rotation in Minecraft: The values are still degrees, but pitch (rot[1])
 *                 values are whole-body pitch instead of head-specific, and rot[2] is whole-body roll.
 * @author daoge_cmd | df-mc
 */
public record ModelSettings(Vector3dc scale, Vector3dc offset, Vector3dc rotation) {
    public static final ModelSettings DEFAULT = new ModelSettings(
            new Vector3d(1, 1, 1),
            new Vector3d(),
            new Vector3d()
    );
}
