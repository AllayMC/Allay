package org.allaymc.api.primitiveshape;

import org.joml.Vector3fc;

import java.awt.*;

/**
 * Represents an arrow rendered by the client's primitive shape system.
 *
 * @author daoge_cmd
 */
public class PrimitiveArrow extends PrimitiveShape {
    /**
     * The end position of the arrow.
     * <p>
     * Can be {@code null}, and in that case that the position will be set to (0, 0, 0) client-side.
     */
    protected Vector3fc endPosition;
    /**
     * The length of the arrow head.
     * <p>
     * Can be {@code null}, and in that case that the arrow head length will be set to 1 client-side.
     */
    protected Float arrowHeadLength;
    /**
     * The radius of the arrow head.
     * <p>
     * Can be {@code null}, and in that case that the arrow head radius will be set to 0.5 client-side.
     */
    protected Float arrowHeadRadius;
    /**
     * The segments of the arrow head, which determines how many segments the arrow head will be divided into.
     * <p>
     * Can be {@code null}, and in that case that the segments will be set to 4 client-side.
     */
    protected Integer arrowHeadSegments;

    /**
     * Creates a new PrimitiveArrow instance with the specified parameters.
     *
     * @param position          the starting position of the arrow; can be {@code null}
     * @param color             the color of the arrow; can be {@code null}
     * @param endPosition       the end position of the arrow; can be {@code null}
     * @param arrowHeadLength   the length of the arrow head; can be {@code null}
     * @param arrowHeadRadius   the radius of the arrow head; can be {@code null}
     * @param arrowHeadSegments the number of segments in the arrow head; can be {@code null}
     * @param scale             the scale of the arrow; can be {@code null}
     */
    public PrimitiveArrow(Vector3fc position, Color color, Vector3fc endPosition, Float arrowHeadLength, Float arrowHeadRadius, Integer arrowHeadSegments, Float scale) {
        super(position, color, scale, null, null, null);
        this.endPosition = endPosition;
        this.arrowHeadLength = arrowHeadLength;
        this.arrowHeadRadius = arrowHeadRadius;
        this.arrowHeadSegments = arrowHeadSegments;
    }

    /**
     * Gets the end position of the arrow.
     *
     * @return the end position of the arrow
     */
    public Vector3fc getEndPosition() {
        return endPosition != null ? endPosition : ZERO_VECTOR;
    }

    /**
     * Sets the end position of the arrow.
     *
     * @param endPosition the end position of the arrow; can be {@code null}
     */
    public void setEndPosition(Vector3fc endPosition) {
        this.endPosition = endPosition;
        this.onChange();
    }

    /**
     * Gets the length of the arrow head.
     *
     * @return the length of the arrow head
     */
    public float getArrowHeadLength() {
        return arrowHeadLength != null ? arrowHeadLength : 1.0f;
    }

    /**
     * Sets the length of the arrow head.
     *
     * @param arrowHeadLength the length of the arrow head; can be {@code null}
     */
    public void setArrowHeadLength(Float arrowHeadLength) {
        this.arrowHeadLength = arrowHeadLength;
        this.onChange();
    }

    /**
     * Gets the radius of the arrow head.
     *
     * @return the radius of the arrow head
     */
    public float getArrowHeadRadius() {
        return arrowHeadRadius != null ? arrowHeadRadius : 0.5f;
    }

    /**
     * Sets the radius of the arrow head.
     *
     * @param arrowHeadRadius the radius of the arrow head; can be {@code null}
     */
    public void setArrowHeadRadius(Float arrowHeadRadius) {
        this.arrowHeadRadius = arrowHeadRadius;
        this.onChange();
    }

    /**
     * Gets the segments of the arrow head.
     *
     * @return the segments of the arrow head
     */
    public int getArrowHeadSegments() {
        return arrowHeadSegments != null ? arrowHeadSegments : 4;
    }

    /**
     * Sets the segments of the arrow head.
     *
     * @param arrowHeadSegments the number of segments in the arrow head; can be {@code null}
     */
    public void setArrowHeadSegments(Integer arrowHeadSegments) {
        this.arrowHeadSegments = arrowHeadSegments;
        this.onChange();
    }
}
