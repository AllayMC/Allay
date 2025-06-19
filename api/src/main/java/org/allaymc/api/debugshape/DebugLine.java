package org.allaymc.api.debugshape;

import lombok.Setter;
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
    @Setter
    protected Vector3fc lineEndPosition;

    /**
     * Creates a new DebugLine with the specified position, color, and end position.
     *
     * @param position        the starting position of the line.
     * @param color           the color of the line.
     * @param lineEndPosition the end position of the line.
     */
    public DebugLine(Vector3fc position, Color color, Vector3fc lineEndPosition) {
        super(position, color);
        this.lineEndPosition = lineEndPosition;
    }

    /**
     * Gets the end position of the line.
     *
     * @return the end position of the line.
     */
    public Vector3fc getLineEndPosition() {
        return lineEndPosition != null ? lineEndPosition : ZERO_VECTOR;
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
                null, null, MathUtils.JOMLVecToCBVec(lineEndPosition),
                null, null, null
        );
    }
}
