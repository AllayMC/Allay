package org.allaymc.api.debugshape;

import lombok.Getter;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The base class for all debug shapes.
 *
 * @author daoge_cmd
 */
public abstract class DebugShape {

    protected static final AtomicLong DEBUG_SHAPE_ID_COUNTER = new AtomicLong(0);
    protected static final Vector3fc ZERO_VECTOR = new Vector3f(0, 0, 0);

    /**
     * The id of this debug shape.
     */
    @Getter
    protected final long id;
    /**
     * The viewers of this debug shape.
     */
    @Getter
    protected final Set<DebugShapeViewer> viewers;
    /**
     * The position of the shape. For most shapes this is the centre of the shape, except
     * {@link DebugLine} and {@link DebugArrow} where this represents the start point of the line.
     * <p>
     * Can be {@code null}, and in that case that the position will be set to (0, 0, 0) client-side.
     */
    protected Vector3fc position;
    /**
     * The color of the shape.
     * <p>
     * Can be {@code null}, and in that case that the color will be set to white client-side.
     */
    protected Color color;

    /**
     * Creates a new debug shape with the specified position, rotation, color, and scale.
     *
     * @param position The position of the shape
     * @param color    the color of the shape
     */
    public DebugShape(Vector3fc position, Color color) {
        this.id = DEBUG_SHAPE_ID_COUNTER.getAndIncrement();
        this.viewers = new HashSet<>();
        this.position = position;
        this.color = color;
    }

    /**
     * Gets the position of this debug shape.
     *
     * @return the position of this debug shape
     */
    public Vector3fc getPosition() {
        return this.position != null ? this.position : ZERO_VECTOR;
    }

    /**
     * Sets the position of this debug shape.
     *
     * @param position the new position of this debug shape
     */
    public void setPosition(Vector3fc position) {
        this.position = position;
        this.onChange();
    }

    /**
     * Gets the color of this debug shape.
     *
     * @return the color of this debug shape
     */
    public Color getColor() {
        return this.color != null ? this.color : Color.WHITE;
    }

    /**
     * Sets the color of this debug shape.
     *
     * @param color the new color of this debug shape
     */
    public void setColor(Color color) {
        this.color = color;
        this.onChange();
    }

    /**
     * @see #addViewer(DebugShapeViewer, boolean)
     */
    public void addViewer(DebugShapeViewer viewer) {
        this.addViewer(viewer, true);
    }

    /**
     * Adds this shape to the specified viewer's side, so that the viewer will see it.
     * Further changes to this shape will be sent to the viewer automatically.
     *
     * @param viewer          the viewer to add this debug shape to
     * @param viewImmediately whether to view this debug shape immediately
     */
    public void addViewer(DebugShapeViewer viewer, boolean viewImmediately) {
        if (this.viewers.add(viewer) && viewImmediately) {
            viewer.viewDebugShape(this);
        }
    }

    /**
     * @see #removeViewer(DebugShapeViewer, boolean)
     */
    public void removeViewer(DebugShapeViewer viewer) {
        this.removeViewer(viewer, true);
    }

    /**
     * Removes this shape from the specified viewer's side. The viewer will no longer see it.
     *
     * @param viewer            the viewer to remove this debug shape from
     * @param removeImmediately whether to remove this debug shape from the viewer immediately
     */
    public void removeViewer(DebugShapeViewer viewer, boolean removeImmediately) {
        if (this.viewers.remove(viewer) && removeImmediately) {
            viewer.removeDebugShape(this);
        }
    }

    /**
     * This method is called whenever the properties of this debug shape changes.
     * It will send the updated data to all viewers of this debug shape.
     */
    protected void onChange() {
        for (var viewer : this.viewers) {
            viewer.viewDebugShape(this);
        }
    }
}
