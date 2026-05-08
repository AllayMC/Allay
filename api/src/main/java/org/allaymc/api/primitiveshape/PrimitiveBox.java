package org.allaymc.api.primitiveshape;

import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.awt.*;

/**
 * Represents a box rendered by the client's primitive shape system.
 *
 * @author daoge_cmd
 */
public class PrimitiveBox extends PrimitiveShape {

    protected static final Vector3fc DEFAULT_BOX_BOUNDS = new Vector3f(1, 1, 1);

    /**
     * The bounds of the box, which is a vector representing the size of the box in each dimension (x, y and z).
     * <p>
     * Can be {@code null}, and in that case that the boxBounds will be set to (1, 1, 1) client-side.
     */
    protected Vector3fc boxBounds;

    /**
     * Creates a new PrimitiveBox with the default position, color, scale and box bounds.
     *
     * @param position  the position of the box in the world; can be {@code null}
     * @param color     the color of the box; can be {@code null}
     * @param scale     the scale of the box, which is a multiplier for the size of the box; can be {@code null}
     * @param boxBounds the bounds of the box, which is a vector representing the size of the box in each dimension (x, y and z); can be {@code null}
     */
    public PrimitiveBox(Vector3fc position, Color color, Float scale, Vector3fc boxBounds) {
        super(position, color, scale, null, null, null);
        this.boxBounds = boxBounds;
    }

    /**
     * Gets the bounds of the box.
     *
     * @return the bounds of the box
     */
    public Vector3fc getBoxBounds() {
        return boxBounds != null ? boxBounds : DEFAULT_BOX_BOUNDS;
    }

    /**
     * Sets the bounds of the box.
     *
     * @param boxBounds the bounds of the box, which is a vector representing the size of the box in each dimension (x, y and z); can be {@code null}
     */
    public void setBoxBounds(Vector3fc boxBounds) {
        this.boxBounds = boxBounds;
        this.onChange();
    }
}
