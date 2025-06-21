package org.allaymc.api.debugshape;

import org.allaymc.api.math.MathUtils;
import org.joml.Vector3fc;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class DebugCircle extends DebugShape {

    /**
     * The scale of the circle, which is a multiplier for the size of the circle.
     * <p>
     * Can be {@code null}, and in that case that the scale will be set to 1 client-side.
     */
    protected Float scale;
    /**
     * The segments of the circle, which determines how many segments the circle will be divided into.
     * <p>
     * Can be {@code null}, and in that case that the segments will be set to 20 client-side.
     */
    protected Integer segments;

    /**
     * Creates a new DebugCircle with the specified position, color, scale and segments.
     *
     * @param position the position of the circle in the world.
     * @param color    the color of the circle.
     * @param scale    the scale of the circle, which is a multiplier for the size of the circle.
     * @param segments the segments of the circle, which determines how many segments the circle will be divided into.
     */
    public DebugCircle(Vector3fc position, Color color, Float scale, Integer segments) {
        super(position, color);
        this.scale = scale;
        this.segments = segments;
    }

    /**
     * Gets the scale of the circle.
     *
     * @return the scale of the circle.
     */
    public float getScale() {
        return scale != null ? scale : 1.0f;
    }

    /**
     * Sets the scale of the circle.
     *
     * @param scale the scale of the circle, which is a multiplier for the size of the circle.
     */
    public void setScale(Float scale) {
        this.scale = scale;
        this.onChange();
    }

    /**
     * Gets the segments of the circle.
     *
     * @return the segments of the circle.
     */
    public int getSegments() {
        return segments != null ? segments : 20;
    }

    /**
     * Sets the segments of the circle.
     *
     * @param segments the segments of the circle, which determines how many segments the circle will be divided into.
     */
    public void setSegments(Integer segments) {
        this.segments = segments;
        this.onChange();
    }

    @Override
    public org.cloudburstmc.protocol.bedrock.data.DebugShape.Type getType() {
        return org.cloudburstmc.protocol.bedrock.data.DebugShape.Type.CIRCLE;
    }

    @Override
    public org.cloudburstmc.protocol.bedrock.data.DebugShape toNetworkData() {
        return new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                id, getType(), MathUtils.JOMLVecToCBVec(position), scale,
                null, null, color,
                null, null, null,
                null, null, segments
        );
    }
}
