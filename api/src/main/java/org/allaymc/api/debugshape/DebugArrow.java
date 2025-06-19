package org.allaymc.api.debugshape;

import lombok.Setter;
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
    @Setter
    protected Vector3fc lineEndPosition;
    /**
     * The length of the arrow head.
     * <p>
     * Can be {@code null}, and in that case that the arrow head length will be set to 1 client-side.
     */
    @Setter
    protected Float arrowHeadLength;
    /**
     * The radius of the arrow head.
     * <p>
     * Can be {@code null}, and in that case that the arrow head radius will be set to 0.5 client-side.
     */
    @Setter
    protected Float arrowHeadRadius;
    /**
     * The segments of the arrow head, which determines how many segments the arrow head will be divided into.
     * <p>
     * Can be {@code null}, and in that case that the segments will be set to 4 client-side.
     */
    @Setter
    protected Integer segments;

    public DebugArrow(Vector3fc position, Color color) {
        super(position, color);
    }

    /**
     * Gets the end position of the arrow.
     *
     * @return the end position of the arrow.
     */
    public Vector3fc getLineEndPosition() {
        return lineEndPosition != null ? lineEndPosition : ZERO_VECTOR;
    }

    /**
     * Gets the length of the arrow head.
     *
     * @return the length of the arrow head.
     */
    public float getArrowHeadLength() {
        return arrowHeadLength != null ? arrowHeadLength : 1.0f;
    }

    /**
     * Gets the radius of the arrow head.
     *
     * @return the radius of the arrow head.
     */
    public float getArrowHeadRadius() {
        return arrowHeadRadius != null ? arrowHeadRadius : 0.5f;
    }

    /**
     * Gets the segments of the arrow head.
     *
     * @return the segments of the arrow head.
     */
    public int getSegments() {
        return segments != null ? segments : 4;
    }

    @Override
    public org.cloudburstmc.protocol.bedrock.data.DebugShape.Type getType() {
        return org.cloudburstmc.protocol.bedrock.data.DebugShape.Type.ARROW;
    }

    @Override
    public org.cloudburstmc.protocol.bedrock.data.DebugShape toNetworkData() {
        return new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                id, getType(), MathUtils.JOMLVecToCBVec(position), null,
                null, null, color,
                null, null, MathUtils.JOMLVecToCBVec(lineEndPosition),
                arrowHeadLength, arrowHeadRadius, segments
        );
    }
}
