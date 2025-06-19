package org.allaymc.api.debugshape;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.DebugShape.Type;
import org.cloudburstmc.protocol.bedrock.packet.ServerScriptDebugDrawerPacket;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.awt.*;
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
     * The position of the shape. For most shapes this is the centre of the shape, except
     * {@link DebugLine} and {@link DebugArrow} where this represents the start point of the line.
     * <p>
     * Can be {@code null}, and in that case that the position will be set to (0, 0, 0) client-side.
     */
    @Setter
    protected Vector3fc position;
    /**
     * The color of the shape.
     * <p>
     * Can be {@code null}, and in that case that the color will be set to white client-side.
     */
    @Setter
    protected Color color;

    /**
     * Creates a new debug shape with the specified position, rotation, color, and scale.
     *
     * @param position The position of the shape.
     * @param color    the color of the shape.
     */
    public DebugShape(Vector3fc position, Color color) {
        this.id = DEBUG_SHAPE_ID_COUNTER.getAndIncrement();
        this.position = position;
        this.color = color;
    }

    /**
     * Gets the position of this debug shape.
     *
     * @return the position of this debug shape.
     */
    public Vector3fc getPosition() {
        return this.position != null ? this.position : ZERO_VECTOR;
    }

    /**
     * Gets the color of this debug shape.
     *
     * @return the color of this debug shape.
     */
    public Color getColor() {
        return this.color != null ? this.color : Color.WHITE;
    }

    /**
     * Removes this shape from the specified player's side, so that the player will no longer see it.
     */
    public void removeFrom(EntityPlayer player) {
        var packet = new ServerScriptDebugDrawerPacket();
        packet.getShapes().add(new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                this.id, null, null,
                null, null, null,
                null, null, null,
                null, null, null, null
        ));
        player.sendPacket(packet);
    }

    /**
     * Gets the type of this debug shape.
     *
     * @return the type of this debug shape
     */
    public abstract Type getType();

    /**
     * Converts this debug shape to a network data representation.
     *
     * @return the network data representation of this debug shape.
     */
    public abstract org.cloudburstmc.protocol.bedrock.data.DebugShape toNetworkData();
}
