package org.allaymc.api.primitiveshape;

import org.joml.Vector3fc;

import java.awt.*;

/**
 * Represents a line rendered by the client's primitive shape system.
 *
 * @author daoge_cmd
 */
public class PrimitiveLine extends PrimitiveShape {

    /**
     * The end position of the line.
     * <p>
     * Can be {@code null}, and in that case that the position will be set to (0, 0, 0) client-side.
     */
    protected Vector3fc endPosition;

    /**
     * Creates a new PrimitiveLine with the specified position, color, and end position.
     *
     * @param position    the starting position of the line; can be {@code null}
     * @param color       the color of the line; can be {@code null}
     * @param endPosition the end position of the line; can be {@code null}
     */
    public PrimitiveLine(Vector3fc position, Color color, Vector3fc endPosition) {
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
     * @param endPosition the new end position of the line; can be {@code null}
     */
    public void setEndPosition(Vector3fc endPosition) {
        this.endPosition = endPosition;
        this.onChange();
    }
}
