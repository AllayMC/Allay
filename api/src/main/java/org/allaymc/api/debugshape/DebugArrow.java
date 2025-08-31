package org.allaymc.api.debugshape;

import org.allaymc.api.math.MathUtils;
import org.joml.Vector3fc;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class DebugArrow extends DebugShape {
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
     * The scale of the arrow head, which is a multiplier for the size of the arrow head.
     * <p>
     * Can be {@code null}, and in that case that the scale will be set to 1 client-side.
     */
    protected Float arrowHeadScale;

    /**
     * Creates a new DebugArrow instance with the specified parameters.
     *
     * @param position          the starting position of the arrow
     * @param color             the color of the arrow
     * @param endPosition       the end position of the arrow
     * @param arrowHeadLength   the length of the arrow head
     * @param arrowHeadRadius   the radius of the arrow head
     * @param arrowHeadSegments the number of segments in the arrow head
     */
    public DebugArrow(Vector3fc position, Color color, Vector3fc endPosition, Float arrowHeadLength, Float arrowHeadRadius, Integer arrowHeadSegments, Float arrowHeadScale) {
        super(position, color);
        this.endPosition = endPosition;
        this.arrowHeadLength = arrowHeadLength;
        this.arrowHeadRadius = arrowHeadRadius;
        this.arrowHeadSegments = arrowHeadSegments;
        this.arrowHeadScale = arrowHeadScale;
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
     * @param endPosition the end position of the arrow
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
     * @param arrowHeadLength the length of the arrow head
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
     * @param arrowHeadRadius the radius of the arrow head
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
     * @param arrowHeadSegments the number of segments in the arrow head
     */
    public void setArrowHeadSegments(Integer arrowHeadSegments) {
        this.arrowHeadSegments = arrowHeadSegments;
        this.onChange();
    }

    /**
     * Gets the scale of the arrow head.
     *
     * @return the scale of the arrow head
     */
    public float getArrowHeadScale() {
        return arrowHeadScale != null ? arrowHeadScale : 1.0f;
    }

    /**
     * Sets the scale of the arrow head.
     *
     * @param arrowHeadScale the scale of the arrow head
     */
    public void setArrowHeadScale(Float arrowHeadScale) {
        this.arrowHeadScale = arrowHeadScale;
        this.onChange();
    }

    @Override
    public org.cloudburstmc.protocol.bedrock.data.DebugShape.Type getType() {
        return org.cloudburstmc.protocol.bedrock.data.DebugShape.Type.ARROW;
    }

    @Override
    public org.cloudburstmc.protocol.bedrock.data.DebugShape toNetworkData() {
        return new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                id, getType(), MathUtils.JOMLVecToCBVec(position), arrowHeadScale,
                null, null, color,
                null, null, MathUtils.JOMLVecToCBVec(endPosition),
                arrowHeadLength, arrowHeadRadius, arrowHeadSegments
        );
    }
}
