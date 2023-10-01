package cn.allay.api.blockentity.component.base;

import cn.allay.api.block.component.event.BlockOnInteractEvent;
import cn.allay.api.block.component.event.BlockOnNeighborUpdateEvent;
import cn.allay.api.block.component.event.BlockOnPlaceEvent;
import cn.allay.api.block.component.event.BlockOnReplaceEvent;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.component.BlockEntityComponent;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.client.Client;
import cn.allay.api.math.position.Position3ic;
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

    default void sendBlockEntityDataPacketTo(Client client) {
        client.sendPacket(createBlockEntityDataPacket());
    }

    default void sendBlockEntityDataPacketToAll() {
        var pos = getPosition();
        var chunk = pos.world().getChunkService().getChunkByLevelPos(pos.x(), pos.z());
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
        return pos.world().getBlockState(pos.x(), pos.y(), pos.z());
    }
}
