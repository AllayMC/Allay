package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.pdc.PersistentDataHolder;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BlockEntityDataPacket;

import java.util.Objects;

/**
 * @author daoge_cmd
 */
public interface BlockEntityBaseComponent extends BlockEntityComponent, PersistentDataHolder {

    /**
     * Gets the type of block entity.
     *
     * @return The type of block entity
     */
    BlockEntityType<? extends BlockEntity> getBlockEntityType();

    /**
     * Gets the position of the block entity.
     *
     * @return The position of the block entity
     */
    Position3ic getPosition();

    /**
     * Gets the dimension of the block entity.
     *
     * @return The dimension of the block entity
     */
    default Dimension getDimension() {
        return getPosition().dimension();
    }

    /**
     * Gets the world of the block entity.
     *
     * @return The world of the block entity
     */
    default World getWorld() {
        return getDimension().getWorld();
    }

    /**
     * Saves the NBT data of the block entity.
     *
     * @return The NBT data of the block entity
     */
    NbtMap saveNBT();

    /**
     * Loads the NBT data into the block entity.
     *
     * @param nbt The NBT data to load
     */
    void loadNBT(NbtMap nbt);

    /**
     * Creates a BlockEntityDataPacket for the block entity.
     *
     * @return The BlockEntityDataPacket for the block entity
     */
    default BlockEntityDataPacket createBlockEntityDataPacket() {
        var packet = new BlockEntityDataPacket();
        var pos = getPosition();
        packet.setBlockPosition(Vector3i.from(pos.x(), pos.y(), pos.z()));
        packet.setData(saveNBT());
        return packet;
    }

    /**
     * @see #sendBlockEntityToViewers(boolean)
     */
    default void sendPacketToViewers(BedrockPacket packet) {
        sendPacketToViewers(packet, true);
    }

    /**
     * Sends a packet to the block entity's viewers.
     *
     * @param packet      the packet to send
     * @param immediately whether the packet should be sent immediately. When {@code false}, the packet
     *                    will be sent in the next tick of the chunk that the block entity is currently in.
     */
    default void sendPacketToViewers(BedrockPacket packet, boolean immediately) {
        var pos = getPosition();
        var chunk = pos.dimension().getChunkManager().getChunkByDimensionPos(pos.x(), pos.z());
        Objects.requireNonNull(chunk, "The chunk located at pos " + pos + " is not loaded!");
        if (immediately) {
            chunk.sendChunkPacket(packet);
        } else {
            chunk.addChunkPacket(packet);
        }
    }

    /**
     * @see #sendBlockEntityToViewers(boolean)
     */
    default void sendBlockEntityToViewers() {
        sendBlockEntityToViewers(true);
    }

    /**
     * Sends the block entity to its viewers.
     *
     * @param immediately whether the packet should be sent immediately. When {@code false}, the packet
     *                    will be sent in the next tick of the chunk that the block entity is currently in.
     */
    default void sendBlockEntityToViewers(boolean immediately) {
        sendPacketToViewers(createBlockEntityDataPacket(), immediately);
    }

    /**
     * Whether the block entity should be sent to the client.
     *
     * @return Whether the block entity should be sent to the client
     */
    default boolean sendToClient() {
        return true;
    }

    /**
     * Gets the block state of the block entity.
     *
     * @return The block state of the block entity
     */
    default BlockState getBlockState() {
        var pos = getPosition();
        return pos.dimension().getBlockState(pos);
    }

    /**
     * Gets the custom name of the block entity.
     *
     * @return The custom name of the block entity, null if not present
     */
    String getCustomName();

    /**
     * Sets the custom name of the block entity.
     *
     * @param customName The custom name of the block entity, can be null to remove the custom name
     */
    void setCustomName(String customName);

    /**
     * Whether the block entity has a custom name.
     *
     * @return Whether the block entity has a custom name
     */
    default boolean hasCustomName() {
        return getCustomName() != null;
    }
}
