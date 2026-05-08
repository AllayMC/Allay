package org.allaymc.api.primitiveshape;

import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The base class for all primitive shapes.
 *
 * @author daoge_cmd
 */
public abstract class PrimitiveShape {

    protected static final AtomicLong PRIMITIVE_SHAPE_ID_COUNTER = new AtomicLong(0);
    protected static final Vector3fc ZERO_VECTOR = new Vector3f(0, 0, 0);

    /**
     * The id of this primitive shape.
     */
    @Getter
    protected final long id;
    /**
     * The viewers of this primitive shape.
     */
    @Getter
    protected final Set<PrimitiveShapeViewer> viewers;
    /**
     * The position of the shape. For most shapes this is the centre of the shape, except
     * {@link PrimitiveLine} and {@link PrimitiveArrow} where this represents the start point of the line.
     * <p>
     * Can be {@code null}, and in that case that the position will be set to (0, 0, 0) client-side.
     */
    protected Vector3fc position;
    /**
     * The scale of the shape.
     * <p>
     * Can be {@code null}, and in that case the client decides the default scale.
     */
    protected Float scale;
    /**
     * The rotation of the shape.
     * <p>
     * Can be {@code null}, and in that case the client decides the default rotation.
     */
    protected Vector3fc rotation;
    /**
     * The total time left before the shape disappears.
     * <p>
     * Can be {@code null}, and in that case the client decides the default lifetime.
     */
    protected Float totalTimeLeft;
    /**
     * The maximum distance from which the shape can be rendered.
     * <p>
     * Can be {@code null}, and in that case the client decides the default render distance.
     */
    protected Float maximumRenderDistance;
    /**
     * The entity this shape attached to. When the shape is attached to an entity, its position will
     * be relative and follow the entity's position. Can be {@code null} if this shape is not attached
     * to any entity.
     */
    @Getter
    protected Entity attachedEntity;
    /**
     * The color of the shape.
     * <p>
     * Can be {@code null}, and in that case that the color will be set to white client-side.
     */
    protected Color color;

    /**
     * Creates a new primitive shape with the specified position and color.
     *
     * @param position the position of the shape; can be {@code null}
     * @param color    the color of the shape; can be {@code null}
     */
    public PrimitiveShape(Vector3fc position, Color color) {
        this(position, color, null, null, null, null);
    }

    /**
     * Creates a new primitive shape with the specified common properties.
     *
     * @param position              the position of the shape; can be {@code null}
     * @param color                 the color of the shape; can be {@code null}
     * @param scale                 the scale of the shape; can be {@code null}
     * @param rotation              the rotation of the shape; can be {@code null}
     * @param totalTimeLeft         the total time left before the shape disappears; can be {@code null}
     * @param maximumRenderDistance the maximum distance from which the shape can be rendered; can be {@code null}
     */
    public PrimitiveShape(Vector3fc position, Color color, Float scale, Vector3fc rotation, Float totalTimeLeft, Float maximumRenderDistance) {
        this.id = PRIMITIVE_SHAPE_ID_COUNTER.getAndIncrement();
        this.viewers = new HashSet<>();
        this.position = position;
        this.color = color;
        this.scale = scale;
        this.rotation = rotation;
        this.totalTimeLeft = totalTimeLeft;
        this.maximumRenderDistance = maximumRenderDistance;
    }

    /**
     * Gets the position of this primitive shape.
     *
     * @return the position of this primitive shape
     */
    public Vector3fc getPosition() {
        return this.position != null ? this.position : ZERO_VECTOR;
    }

    /**
     * Sets the position of this primitive shape.
     *
     * @param position the new position of this primitive shape; can be {@code null}
     */
    public void setPosition(Vector3fc position) {
        this.position = position;
        this.onChange();
    }

    /**
     * Gets the scale of this primitive shape.
     *
     * @return the scale of this primitive shape, or {@code null} if the client should use its default
     */
    public Float getScale() {
        return this.scale;
    }

    /**
     * Sets the scale of this primitive shape.
     *
     * @param scale the new scale of this primitive shape; can be {@code null}
     */
    public void setScale(Float scale) {
        this.scale = scale;
        this.onChange();
    }

    /**
     * Gets the rotation of this primitive shape.
     *
     * @return the rotation of this primitive shape, or {@code null} if the client should use its default
     */
    public Vector3fc getRotation() {
        return this.rotation;
    }

    /**
     * Sets the rotation of this primitive shape.
     *
     * @param rotation the new rotation of this primitive shape; can be {@code null}
     */
    public void setRotation(Vector3fc rotation) {
        this.rotation = rotation;
        this.onChange();
    }

    /**
     * Gets the total time left before this primitive shape disappears.
     *
     * @return the total time left, or {@code null} if the client should use its default
     */
    public Float getTotalTimeLeft() {
        return this.totalTimeLeft;
    }

    /**
     * Sets the total time left before this primitive shape disappears.
     *
     * @param totalTimeLeft the total time left; can be {@code null}
     */
    public void setTotalTimeLeft(Float totalTimeLeft) {
        this.totalTimeLeft = totalTimeLeft;
        this.onChange();
    }

    /**
     * Gets the maximum distance from which this primitive shape can be rendered.
     *
     * @return the maximum render distance, or {@code null} if the client should use its default
     */
    public Float getMaximumRenderDistance() {
        return this.maximumRenderDistance;
    }

    /**
     * Sets the maximum distance from which this primitive shape can be rendered.
     *
     * @param maximumRenderDistance the maximum render distance; can be {@code null}
     */
    public void setMaximumRenderDistance(Float maximumRenderDistance) {
        this.maximumRenderDistance = maximumRenderDistance;
        this.onChange();
    }

    /**
     * Sets the entity to which this primitive shape is attached.
     *
     * @param attachedEntity the entity to attach this primitive shape to; can be {@code null} to detach
     *                       from any currently attached entity
     */
    public void setAttachedEntity(Entity attachedEntity) {
        this.attachedEntity = attachedEntity;
        this.onChange();
    }

    /**
     * Gets the color of this primitive shape.
     *
     * @return the color of this primitive shape
     */
    public Color getColor() {
        return this.color != null ? this.color : Color.WHITE;
    }

    /**
     * Sets the color of this primitive shape.
     *
     * @param color the new color of this primitive shape; can be {@code null}
     */
    public void setColor(Color color) {
        this.color = color;
        this.onChange();
    }

    /**
     * @see #addViewer(PrimitiveShapeViewer, boolean)
     */
    public void addViewer(PrimitiveShapeViewer viewer) {
        this.addViewer(viewer, true);
    }

    /**
     * Adds this shape to the specified viewer's side, so that the viewer will see it.
     * Further changes to this shape will be sent to the viewer automatically.
     *
     * @param viewer          the viewer to add this primitive shape to
     * @param viewImmediately whether to view this primitive shape immediately
     */
    public void addViewer(PrimitiveShapeViewer viewer, boolean viewImmediately) {
        if (this.viewers.add(viewer) && viewImmediately) {
            viewer.viewPrimitiveShape(this);
        }
    }

    /**
     * @see #removeViewer(PrimitiveShapeViewer, boolean)
     */
    public void removeViewer(PrimitiveShapeViewer viewer) {
        this.removeViewer(viewer, true);
    }

    /**
     * Removes this shape from the specified viewer's side. The viewer will no longer see it.
     *
     * @param viewer            the viewer to remove this primitive shape from
     * @param removeImmediately whether to remove this primitive shape from the viewer immediately
     */
    public void removeViewer(PrimitiveShapeViewer viewer, boolean removeImmediately) {
        if (this.viewers.remove(viewer) && removeImmediately) {
            viewer.removePrimitiveShape(this);
        }
    }

    /**
     * This method is called whenever the properties of this primitive shape changes.
     * It will send the updated data to all viewers of this primitive shape.
     */
    protected void onChange() {
        for (var viewer : this.viewers) {
            viewer.viewPrimitiveShape(this);
        }
    }
}
