package org.allaymc.api.debugshape;

import org.allaymc.api.math.MathUtils;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class DebugBox extends DebugShape {

    protected static final Vector3fc DEFAULT_BOX_BOUNDS = new Vector3f(1, 1, 1);

    /**
     * The scale of the box, which is a multiplier for the size of the box.
     * <p>
     * Can be {@code null}, and in that case that the scale will be set to 1 client-side.
     */
    protected Float scale;
    /**
     * The bounds of the box, which is a vector representing the size of the box in each dimension (x, y and z).
     * <p>
     * Can be {@code null}, and in that case that the boxBounds will be set to (1, 1, 1) client-side.
     */
    protected Vector3fc boxBounds;

    /**
     * Creates a new DebugBox with the default position, color, scale and box bounds.
     *
     * @param position  the position of the box in the world.
     * @param color     the color of the box.
     * @param scale     the scale of the box, which is a multiplier for the size of the box.
     * @param boxBounds the bounds of the box, which is a vector representing the size of the box in each dimension (x, y and z).
     */
    public DebugBox(Vector3fc position, Color color, Float scale, Vector3fc boxBounds) {
        super(position, color);
        this.scale = scale;
        this.boxBounds = boxBounds;
    }

    /**
     * Gets the scale of the box.
     *
     * @return the scale of the box.
     */
    public float getScale() {
        return scale != null ? scale : 1.0f;
    }

    /**
     * Sets the scale of the box.
     *
     * @param scale the scale of the box, which is a multiplier for the size of the box.
     */
    public void setScale(Float scale) {
        this.scale = scale;
        this.onChange();
    }

    /**
     * Gets the bounds of the box.
     *
     * @return the bounds of the box.
     */
    public Vector3fc getBoxBounds() {
        return boxBounds != null ? boxBounds : DEFAULT_BOX_BOUNDS;
    }

    /**
     * Sets the bounds of the box.
     *
     * @param boxBounds the bounds of the box, which is a vector representing the size of the box in each dimension (x, y and z).
     */
    public void setBoxBounds(Vector3fc boxBounds) {
        this.boxBounds = boxBounds;
        this.onChange();
    }

    @Override
    public org.cloudburstmc.protocol.bedrock.data.DebugShape.Type getType() {
        return org.cloudburstmc.protocol.bedrock.data.DebugShape.Type.BOX;
    }

    @Override
    public org.cloudburstmc.protocol.bedrock.data.DebugShape toNetworkData() {
        return new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                id, getType(), MathUtils.JOMLVecToCBVec(position), scale,
                null, null, color,
                null, MathUtils.JOMLVecToCBVec(boxBounds), null,
                null, null, null
        );
    }
}
