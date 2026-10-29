package org.allaymc.api.primitiveshape;

import org.joml.Vector3fc;

import java.awt.*;

/**
 * Represents a sphere rendered by the client's primitive shape system.
 *
 * @author daoge_cmd
 */
public class PrimitiveSphere extends PrimitiveShape {

    /**
     * The segments of the sphere, which determines how many segments the sphere will be divided into.
     * <p>
     * Can be {@code null}, and in that case that the segments will be set to 20 client-side.
     */
    protected Integer segments;

    /**
     * Creates a new PrimitiveSphere with the specified position, color, scale and segments.
     *
     * @param position the position of the sphere in the world; can be {@code null}
     * @param color    the color of the sphere; can be {@code null}
     * @param scale    the scale of the sphere, which is a multiplier for the size of the sphere; can be {@code null}
     * @param segments the segments of the sphere, which determines how many segments the sphere will be divided into; can be {@code null}
     */
    public PrimitiveSphere(Vector3fc position, Color color, Float scale, Integer segments) {
        super(position, color, scale, null, null, null);
        this.segments = segments;
    }

    /**
     * Gets the segments of the sphere.
     *
     * @return the segments of the sphere
     */
    public int getSegments() {
        return segments != null ? segments : 20;
    }

    /**
     * Sets the segments of the sphere.
     *
     * @param segments the segments of the sphere, which determines how many segments the sphere will be divided into; can be {@code null}
     */
    public void setSegments(Integer segments) {
        this.segments = segments;
        this.onChange();
    }
}
