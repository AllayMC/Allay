package org.allaymc.api.blockentity.component.base;

import org.allaymc.api.block.component.event.BlockOnInteractEvent;
import org.allaymc.api.block.component.event.BlockOnNeighborUpdateEvent;
import org.allaymc.api.block.component.event.BlockOnPlaceEvent;
import org.allaymc.api.block.component.event.BlockOnReplaceEvent;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityComponent;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.math.position.Position3ic;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.BlockEntityDataPacket;

import java.util.Objects;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityBaseComponent extends BlockEntityComponent {

    BlockEntityType<? extends BlockEntity> getBlockEntityType();

    Position3ic getPosition();

    NbtMap saveNBT();

    void loadNBT(NbtMap nbt);

    void onNeighborUpdate(BlockOnNeighborUpdateEvent event);

    void onPlace(BlockOnPlaceEvent event);

    void onReplace(BlockOnReplaceEvent event);

    void onInteract(BlockOnInteractEvent event);

    default BlockEntityDataPacket createBlockEntityDataPacket() {
        var packet = new BlockEntityDataPacket();
        var pos = getPosition();
        packet.setBlockPosition(Vector3i.from(pos.x(), pos.y(), pos.z()));
        packet.setData(saveNBT());
        return packet;
    }

    default void sendBlockEntityDataPacketTo(EntityPlayer player) {
        player.sendPacket(createBlockEntityDataPacket());
    }

    default void sendBlockEntityDataPacketToAll() {
        var pos = getPosition();
        var chunk = pos.dimension().getChunkService().getChunkByLevelPos(pos.x(), pos.z());
        Objects.requireNonNull(chunk, "The chunk located at pos " + pos + " is not loaded!");
        var pk = createBlockEntityDataPacket();
        chunk.sendChunkPacket(pk);
    }

    default void tick() {}

    default boolean sendToClient() {
        return true;
    }

    default BlockState getBlockState() {
        var pos = getPosition();
        return pos.dimension().getBlockState(pos.x(), pos.y(), pos.z());
    }
}
