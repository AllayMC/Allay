package org.allaymc.api.primitiveshape;

import org.joml.Vector3fc;

import java.awt.*;

/**
 * {@code PrimitiveSphere} represents a visual sphere whose segment count controls its
 * tessellation. Position, color, and scale follow the shared primitive-shape contract. The
 * getter supplies twenty segments when no explicit count is stored, and setters notify
 * registered viewers.
 * @author daoge_cmd
 */
public class PrimitiveSphere extends PrimitiveShape {

    /**
     * {@code segments} stores the tessellation count passed to network conversion.
     * {@code getSegments()} supplies {@code 20} when this value is {@code null}.
     */
    protected Integer segments;

    /**
     * Creates a {@code PrimitiveSphere} with the supplied rendering properties and no
     * registered viewers. The inherited constructor retains supplied vectors without
     * copying; later setter calls publish property changes to viewers registered through
     * {@code addViewer}.
     */
    public PrimitiveSphere(Vector3fc position, Color color, Float scale, Integer segments) {
        super(position, color, scale, null, null, null);
        this.segments = segments;
    }

    /**
     * Returns the effective segment count used during network conversion. A stored
     * {@code null} selects {@code 20}, and the supplied numeric value is otherwise
     * returned without range validation.
     */
    public int getSegments() {
        // Supply an explicit effective value here because NetworkHelper serializes this getter
        // result into the protocol shape.
        return segments != null ? segments : 20;
    }

    /**
     * Stores the supplied segment count and synchronously sends this shape to every
     * registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored.
     */
    public void setSegments(Integer segments) {
        this.segments = segments;
        this.onChange();
    }
}
