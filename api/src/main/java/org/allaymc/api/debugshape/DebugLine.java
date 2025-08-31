package org.allaymc.api.debugshape;

import org.allaymc.api.math.MathUtils;
import org.joml.Vector3fc;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class DebugLine extends DebugShape {

    /**
     * The end position of the line.
     * <p>
     * Can be {@code null}, and in that case that the position will be set to (0, 0, 0) client-side.
     */
    protected Vector3fc endPosition;

    /**
     * Creates a new DebugLine with the specified position, color, and end position.
     *
     * @param position    the starting position of the line
     * @param color       the color of the line
     * @param endPosition the end position of the line
     */
    public DebugLine(Vector3fc position, Color color, Vector3fc endPosition) {
        super(position, color);
        this.endPosition = endPosition;
    }

    /**
     * Gets the end position of the line.
     *
     * @return the end position of the line
     */
    public Vector3fc getEndPosition() {
        return endPosition != null ? endPosition : ZERO_VECTOR;
    }

    /**
     * Sets the end position of the line.
     *
     * @param endPosition the new end position of the line
     */
    public void setEndPosition(Vector3fc endPosition) {
        this.endPosition = endPosition;
        this.onChange();
    }

    @Override
    public org.cloudburstmc.protocol.bedrock.data.DebugShape.Type getType() {
        return org.cloudburstmc.protocol.bedrock.data.DebugShape.Type.LINE;
    }

    @Override
    public org.cloudburstmc.protocol.bedrock.data.DebugShape toNetworkData() {
        return new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                id, getType(), MathUtils.JOMLVecToCBVec(position), null,
                null, null, color,
                null, null, MathUtils.JOMLVecToCBVec(endPosition),
                null, null, null
        );
    }
}
