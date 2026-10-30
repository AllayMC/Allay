package org.allaymc.api.primitiveshape;

import org.joml.Vector3fc;

import java.awt.*;

/**
 * {@code PrimitiveArrow} represents a visual arrow with a start point, end point, and
 * configurable arrowhead. The inherited position supplies the start point, and endpoint
 * and arrowhead setters notify registered viewers. Nullable arrowhead properties have
 * explicit getter defaults before network conversion.
 * @author daoge_cmd
 */
public class PrimitiveArrow extends PrimitiveShape {
    /**
     * {@code endPosition} holds the endpoint vector without copying. {@code null} selects
     * the shared origin, and direct vector mutation bypasses the viewer notifications
     * performed by the setter.
     */
    protected Vector3fc endPosition;
    /**
     * {@code arrowHeadLength} stores the arrowhead length. {@code getArrowHeadLength()}
     * supplies {@code 1.0f} when this value is {@code null}.
     */
    protected Float arrowHeadLength;
    /**
     * {@code arrowHeadRadius} stores the arrowhead radius. {@code getArrowHeadRadius()}
     * supplies {@code 0.5f} when this value is {@code null}.
     */
    protected Float arrowHeadRadius;
    /**
     * {@code arrowHeadSegments} stores the arrowhead tessellation count.
     * {@code getArrowHeadSegments()} supplies {@code 4} when this value is {@code null}.
     */
    protected Integer arrowHeadSegments;

    /**
     * Creates a {@code PrimitiveArrow} with the supplied rendering properties and no
     * registered viewers. The inherited constructor retains supplied vectors without
     * copying; later setter calls publish property changes to viewers registered through
     * {@code addViewer}.
     */
    public PrimitiveArrow(Vector3fc position, Color color, Vector3fc endPosition, Float arrowHeadLength, Float arrowHeadRadius, Integer arrowHeadSegments, Float scale) {
        super(position, color, scale, null, null, null);
        this.endPosition = endPosition;
        this.arrowHeadLength = arrowHeadLength;
        this.arrowHeadRadius = arrowHeadRadius;
        this.arrowHeadSegments = arrowHeadSegments;
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

    /**
     * Returns the effective arrowhead length used during network conversion. A stored
     * {@code null} selects {@code 1.0f}, and the supplied numeric value is otherwise
     * returned without range validation.
     */
    public float getArrowHeadLength() {
        // Supply an explicit effective value here because NetworkHelper serializes this getter
        // result into the protocol shape.
        return arrowHeadLength != null ? arrowHeadLength : 1.0f;
    }

    /**
     * Stores the supplied arrowhead length and synchronously sends this shape to every
     * registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored.
     */
    public void setArrowHeadLength(Float arrowHeadLength) {
        this.arrowHeadLength = arrowHeadLength;
        this.onChange();
    }

    /**
     * Returns the effective arrowhead radius used during network conversion. A stored
     * {@code null} selects {@code 0.5f}, and the supplied numeric value is otherwise
     * returned without range validation.
     */
    public float getArrowHeadRadius() {
        // Supply an explicit effective value here because NetworkHelper serializes this getter
        // result into the protocol shape.
        return arrowHeadRadius != null ? arrowHeadRadius : 0.5f;
    }

    /**
     * Stores the supplied arrowhead radius and synchronously sends this shape to every
     * registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored.
     */
    public void setArrowHeadRadius(Float arrowHeadRadius) {
        this.arrowHeadRadius = arrowHeadRadius;
        this.onChange();
    }

    /**
     * Returns the effective arrowhead segment count used during network conversion. A
     * stored {@code null} selects {@code 4}, and the supplied numeric value is otherwise
     * returned without range validation.
     */
    public int getArrowHeadSegments() {
        // Supply an explicit effective value here because NetworkHelper serializes this getter
        // result into the protocol shape.
        return arrowHeadSegments != null ? arrowHeadSegments : 4;
    }

    /**
     * Stores the supplied arrowhead segment count and synchronously sends this shape to
     * every registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored.
     */
    public void setArrowHeadSegments(Integer arrowHeadSegments) {
        this.arrowHeadSegments = arrowHeadSegments;
        this.onChange();
    }
}
