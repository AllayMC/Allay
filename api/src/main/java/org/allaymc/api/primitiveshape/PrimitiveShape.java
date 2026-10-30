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
 * {@code PrimitiveShape} represents mutable visual geometry sent to registered shape
 * viewers. Each instance has an identifier and rendering properties, and property setters
 * synchronously notify the current viewers. Optional properties either use the explicit
 * getter fallback or remain {@code null} for protocol omission. The viewer set and
 * rendering vectors are shared mutable state; callers must serialize changes with
 * iteration and use setters when viewers need an update.
 * @author daoge_cmd
 */
public abstract class PrimitiveShape {

    /**
     * {@code PRIMITIVE_SHAPE_ID_COUNTER} allocates successive identifiers for all
     * primitive shape instances. Atomic increments coordinate identifier allocation only;
     * they do not synchronize a shape's properties or viewers.
     */
    protected static final AtomicLong PRIMITIVE_SHAPE_ID_COUNTER = new AtomicLong(0);
    /**
     * {@code ZERO_VECTOR} supplies the shared origin returned for absent positions and
     * endpoints. Callers must leave this mutable implementation unchanged even though its
     * declared type exposes only reads.
     */
    protected static final Vector3fc ZERO_VECTOR = new Vector3f(0, 0, 0);

    /**
     * Identifier allocated when this shape is constructed.
     */
    @Getter
    protected final long id;
    /**
     * {@code viewers} stores destinations for automatic property updates. The generated
     * getter exposes the live set, so direct edits change registration without sending
     * display or removal requests.
     */
    @Getter
    protected final Set<PrimitiveShapeViewer> viewers;
    /**
     * {@code position} stores the center, or the start point for lines and arrows. The
     * vector is retained without copying; {@code null} selects the shared origin in
     * {@code getPosition()}.
     */
    protected Vector3fc position;
    /**
     * {@code scale} stores the rendering scale multiplier. A {@code null} value remains
     * absent during network conversion so the client selects its default.
     */
    protected Float scale;
    /**
     * {@code rotation} holds the supplied rotation vector without copying. A {@code null}
     * value remains absent during network conversion, and direct vector mutation does not
     * notify viewers.
     */
    protected Vector3fc rotation;
    /**
     * {@code totalTimeLeft} stores the remaining rendering lifetime passed to the
     * protocol. A {@code null} value omits an explicit lifetime; this class does not count
     * it down.
     */
    protected Float totalTimeLeft;
    /**
     * {@code maximumRenderDistance} stores the client rendering distance limit. A
     * {@code null} value leaves that limit absent in the converted protocol shape.
     */
    protected Float maximumRenderDistance;
    /**
     * {@code attachedEntity} identifies the entity whose position anchors this shape. A
     * {@code null} value detaches the shape, and network conversion sends no entity
     * identifier.
     */
    @Getter
    protected Entity attachedEntity;
    /**
     * {@code color} stores the supplied rendering color. A {@code null} value selects
     * {@link Color#WHITE} in {@code getColor()} before network conversion.
     */
    protected Color color;

    /**
     * Creates a shape with a newly allocated identifier and an empty viewer set. The
     * supplied rendering values are retained without copying, and optional {@code null}
     * values follow their getter or protocol defaults.
     */
    public PrimitiveShape(Vector3fc position, Color color) {
        this(position, color, null, null, null, null);
    }

    /**
     * Creates a shape with a newly allocated identifier and an empty viewer set. The
     * supplied rendering values are retained without copying, and optional {@code null}
     * values follow their getter or protocol defaults.
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
     * Returns the stored position, or shared origin when {@code position} is {@code null}.
     * The returned vector is not copied, so callers must leave it unchanged or use the
     * setter to publish an update.
     */
    public Vector3fc getPosition() {
        // Supply an explicit effective value here because NetworkHelper serializes this getter
        // result into the protocol shape.
        return this.position != null ? this.position : ZERO_VECTOR;
    }

    /**
     * Stores the supplied position and synchronously sends this shape to every registered
     * viewer. The notification happens even when the value is unchanged; failures from a
     * viewer propagate after the property is stored. The supplied {@code position} vector
     * is retained without copying.
     */
    public void setPosition(Vector3fc position) {
        this.position = position;
        this.onChange();
    }

    /**
     * Returns the stored scale multiplier used during network conversion. A {@code null}
     * result leaves that rendering option unspecified for the client, rather than
     * supplying an explicit value.
     */
    public Float getScale() {
        return this.scale;
    }

    /**
     * Stores the supplied scale multiplier and synchronously sends this shape to every
     * registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored.
     */
    public void setScale(Float scale) {
        this.scale = scale;
        this.onChange();
    }

    /**
     * Returns the stored rotation vector used during network conversion. A {@code null}
     * result leaves that rendering option unspecified for the client; a non-null vector
     * remains shared with this shape.
     */
    public Vector3fc getRotation() {
        return this.rotation;
    }

    /**
     * Stores the supplied rotation vector and synchronously sends this shape to every
     * registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored. The supplied
     * {@code rotation} vector is retained without copying.
     */
    public void setRotation(Vector3fc rotation) {
        this.rotation = rotation;
        this.onChange();
    }

    /**
     * Returns the stored remaining rendering lifetime used during network conversion. A
     * {@code null} result leaves that rendering option unspecified for the client, rather
     * than supplying an explicit value.
     */
    public Float getTotalTimeLeft() {
        return this.totalTimeLeft;
    }

    /**
     * Stores the supplied remaining rendering lifetime and synchronously sends this shape
     * to every registered viewer. The notification happens even when the value is
     * unchanged; failures from a viewer propagate after the property is stored.
     */
    public void setTotalTimeLeft(Float totalTimeLeft) {
        this.totalTimeLeft = totalTimeLeft;
        this.onChange();
    }

    /**
     * Returns the stored rendering distance limit used during network conversion. A
     * {@code null} result leaves that rendering option unspecified for the client, rather
     * than supplying an explicit value.
     */
    public Float getMaximumRenderDistance() {
        return this.maximumRenderDistance;
    }

    /**
     * Stores the supplied rendering distance limit and synchronously sends this shape to
     * every registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored.
     */
    public void setMaximumRenderDistance(Float maximumRenderDistance) {
        this.maximumRenderDistance = maximumRenderDistance;
        this.onChange();
    }

    /**
     * Stores the supplied attached entity and synchronously sends this shape to every
     * registered viewer. The notification happens even when the value is unchanged;
     * failures from a viewer propagate after the property is stored. A {@code null} value
     * detaches the shape.
     */
    public void setAttachedEntity(Entity attachedEntity) {
        this.attachedEntity = attachedEntity;
        this.onChange();
    }

    /**
     * Returns the stored rendering color or {@link Color#WHITE} when absent. Network
     * conversion uses this effective value, so a missing color is sent as white rather
     * than omitted.
     */
    public Color getColor() {
        // Supply an explicit effective value here because NetworkHelper serializes this getter
        // result into the protocol shape.
        return this.color != null ? this.color : Color.WHITE;
    }

    /**
     * Stores the supplied color and synchronously sends this shape to every registered
     * viewer. The notification happens even when the value is unchanged; failures from a
     * viewer propagate after the property is stored.
     */
    public void setColor(Color color) {
        this.color = color;
        this.onChange();
    }

    /**
     * Registers the viewer for subsequent property updates and requests immediate display
     * after the first successful registration. A repeated registration sends no additional
     * request because the viewer is already present.
     */
    public void addViewer(PrimitiveShapeViewer viewer) {
        this.addViewer(viewer, true);
    }

    /**
     * Registers the viewer for subsequent property updates. A successful membership change
     * requests client display only when {@code viewImmediately} is {@code true}; repeated
     * calls with unchanged membership send no request.
     */
    public void addViewer(PrimitiveShapeViewer viewer, boolean viewImmediately) {
        if (this.viewers.add(viewer) && viewImmediately) {
            viewer.viewPrimitiveShape(this);
        }
    }

    /**
     * Unregisters the viewer from subsequent property updates and requests immediate
     * removal after a successful membership change. Removing an unregistered viewer sends
     * no request because no membership changed.
     */
    public void removeViewer(PrimitiveShapeViewer viewer) {
        this.removeViewer(viewer, true);
    }

    /**
     * Unregisters the viewer from subsequent property updates. A successful membership
     * change requests client removal only when {@code removeImmediately} is {@code true};
     * repeated calls with unchanged membership send no request.
     */
    public void removeViewer(PrimitiveShapeViewer viewer, boolean removeImmediately) {
        if (this.viewers.remove(viewer) && removeImmediately) {
            viewer.removePrimitiveShape(this);
        }
    }

    /**
     * Synchronously sends the current shape to every registered viewer. Callers must avoid
     * modifying {@code viewers} during this iteration, and a viewer exception stops
     * delivery to later viewers.
     */
    protected void onChange() {
        for (var viewer : this.viewers) {
            viewer.viewPrimitiveShape(this);
        }
    }
}
