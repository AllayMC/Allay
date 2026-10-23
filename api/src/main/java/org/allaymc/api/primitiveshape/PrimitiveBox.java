package org.allaymc.api.primitiveshape;

import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.awt.*;

/**
 * {@code PrimitiveBox} represents a visual box with per-axis bounds and an inherited
 * position, color, and scale. The bounds describe its size along each coordinate axis.
 * Setters notify registered viewers, while an absent bounds vector uses a shared unit-size
 * fallback.
 * @author daoge_cmd
 */
public class PrimitiveBox extends PrimitiveShape {

    /**
     * {@code DEFAULT_BOX_BOUNDS} supplies the shared unit-size vector returned when box
     * bounds are absent. Callers must leave its mutable implementation unchanged.
     */
    protected static final Vector3fc DEFAULT_BOX_BOUNDS = new Vector3f(1, 1, 1);

    /**
     * {@code boxBounds} stores the per-axis box size without copying the vector.
     * {@code null} selects {@code DEFAULT_BOX_BOUNDS}, and direct vector mutation bypasses
     * notifications.
     */
    protected Vector3fc boxBounds;

    /**
     * Creates a {@code PrimitiveBox} with the supplied rendering properties and no
     * registered viewers. The inherited constructor retains supplied vectors without
     * copying; later setter calls publish property changes to viewers registered through
     * {@code addViewer}.
     */
    public PrimitiveBox(Vector3fc position, Color color, Float scale, Vector3fc boxBounds) {
        super(position, color, scale, null, null, null);
        this.boxBounds = boxBounds;
    }

    /**
     * Returns the stored per-axis bounds, or shared unit-size bounds when
     * {@code boxBounds} is {@code null}. The returned vector is not copied, so callers
     * must leave it unchanged or use the setter to publish an update.
     */
    public Vector3fc getBoxBounds() {
        // Supply an explicit effective value here because NetworkHelper serializes this getter
        // result into the protocol shape.
        return boxBounds != null ? boxBounds : DEFAULT_BOX_BOUNDS;
    }

    /**
     * Stores the supplied per-axis bounds and synchronously sends this shape to every
     * registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored. The supplied
     * {@code boxBounds} vector is retained without copying.
     */
    public void setBoxBounds(Vector3fc boxBounds) {
        this.boxBounds = boxBounds;
        this.onChange();
    }
}
