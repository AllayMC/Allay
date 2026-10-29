package org.allaymc.api.primitiveshape;

import org.joml.Vector3fc;

import java.awt.*;

/**
 * Represents a circle rendered by the client's primitive shape system.
 *
 * @author daoge_cmd
 */
public class PrimitiveCircle extends PrimitiveShape {

    /**
     * The segments of the circle, which determines how many segments the circle will be divided into.
     * <p>
     * Can be {@code null}, and in that case that the segments will be set to 20 client-side.
     */
    protected Integer segments;

    /**
     * Creates a new PrimitiveCircle with the specified position, color, scale and segments.
     *
     * @param position the position of the circle in the world; can be {@code null}
     * @param color    the color of the circle; can be {@code null}
     * @param scale    the scale of the circle, which is a multiplier for the size of the circle; can be {@code null}
     * @param segments the segments of the circle, which determines how many segments the circle will be divided into; can be {@code null}
     */
    public PrimitiveCircle(Vector3fc position, Color color, Float scale, Integer segments) {
        super(position, color, scale, null, null, null);
        this.segments = segments;
    }

    /**
     * Gets the segments of the circle.
     *
     * @return the segments of the circle
     */
    public int getSegments() {
        return segments != null ? segments : 20;
    }

    /**
     * Sets the segments of the circle.
     *
     * @param segments the segments of the circle, which determines how many segments the circle will be divided into; can be {@code null}
     */
    public void setSegments(Integer segments) {
        this.segments = segments;
        this.onChange();
    }
}
