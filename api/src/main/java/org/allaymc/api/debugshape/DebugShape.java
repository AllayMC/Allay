package org.allaymc.api.debugshape;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.DebugShape.Type;
import org.cloudburstmc.protocol.bedrock.packet.ServerScriptDebugDrawerPacket;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.awt.*;
import java.util.Map;
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
    protected final Map<Long, EntityPlayer> viewers;
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
        this.viewers = new Long2ObjectOpenHashMap<>();
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
     * @see #addViewer(EntityPlayer, boolean)
     */
    public void addViewer(EntityPlayer player) {
        this.addViewer(player, true);
    }

    /**
     * Adds this shape to the specified player's side, so that the player will see it.
     * Further changes to this shape will be sent to the player automatically.
     *
     * @param player                the player to add this shape to
     * @param sendPacketImmediately whether to send the data of this debug shape to the player immediately
     */
    public void addViewer(EntityPlayer player, boolean sendPacketImmediately) {
        if (this.viewers.put(player.getRuntimeId(), player) == null && sendPacketImmediately) {
            var packet = new ServerScriptDebugDrawerPacket();
            packet.getShapes().add(this.toNetworkData());
            player.sendPacket(packet);
        }
    }

    /**
     * @see #removeViewer(EntityPlayer, boolean)
     */
    public void removeViewer(EntityPlayer player) {
        this.removeViewer(player, true);
    }

    /**
     * Removes this shape from the specified player's side. The player will no longer see it.
     *
     * @param player                the player to remove this shape from
     * @param sendPacketImmediately whether to remove this debug shape from the player immediately
     */
    public void removeViewer(EntityPlayer player, boolean sendPacketImmediately) {
        if (this.viewers.remove(player.getRuntimeId()) != null && sendPacketImmediately) {
            var packet = new ServerScriptDebugDrawerPacket();
            packet.getShapes().add(createRemovalNotice());
            player.sendPacket(packet);
        }
    }

    /**
     * This method is called whenever the properties of this debug shape changes.
     * It will send the updated data to all viewers of this debug shape.
     */
    protected void onChange() {
        for (EntityPlayer viewer : this.viewers.values()) {
            var packet = new ServerScriptDebugDrawerPacket();
            packet.getShapes().add(this.toNetworkData());
            viewer.sendPacket(packet);
        }
    }

    /**
     * Gets the type of this debug shape.
     *
     * @return the type of this debug shape
     */
    public abstract Type getType();

    /**
     * Creates a removal notice for this debug shape.
     * This is used to notify the client that this debug shape should be removed.
     *
     * @return a removal notice for this debug shape
     */
    public org.cloudburstmc.protocol.bedrock.data.DebugShape createRemovalNotice() {
        return new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                this.id, null, null,
                null, null, null,
                null, null, null,
                null, null, null, null
        );
    }

    /**
     * Converts this debug shape to a network data representation.
     *
     * @return the network data representation of this debug shape
     */
    public abstract org.cloudburstmc.protocol.bedrock.data.DebugShape toNetworkData();
}
