package org.allaymc.api.primitiveshape;

import org.joml.Vector3fc;

import java.awt.*;

/**
 * {@code PrimitiveLine} represents a visual line between an inherited start position and
 * an endpoint. The endpoint is retained by reference and defaults to the shared origin
 * vector when absent. Endpoint changes made through the setter notify registered viewers.
 * @author daoge_cmd
 */
public class PrimitiveLine extends PrimitiveShape {

    /**
     * {@code endPosition} holds the endpoint vector without copying. {@code null} selects
     * the shared origin, and direct vector mutation bypasses the viewer notifications
     * performed by the setter.
     */
    protected Vector3fc endPosition;

    /**
     * Creates a {@code PrimitiveLine} with the supplied rendering properties and no
     * registered viewers. The inherited constructor retains supplied vectors without
     * copying; later setter calls publish property changes to viewers registered through
     * {@code addViewer}.
     */
    public PrimitiveLine(Vector3fc position, Color color, Vector3fc endPosition) {
        super(position, color);
        this.endPosition = endPosition;
    }

    /**
     * Returns the stored endpoint vector, or shared origin when {@code endPosition} is
     * {@code null}. The returned vector is not copied, so callers must leave it unchanged
     * or use the setter to publish an update.
     */
    public Vector3fc getEndPosition() {
        // Supply an explicit effective value here because NetworkHelper serializes this getter
        // result into the protocol shape.
        return endPosition != null ? endPosition : ZERO_VECTOR;
    }

    /**
     * Stores the supplied endpoint vector and synchronously sends this shape to every
     * registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored. The supplied
     * {@code endPosition} vector is retained without copying.
     */
    public void setEndPosition(Vector3fc endPosition) {
        this.endPosition = endPosition;
        this.onChange();
    }
}
