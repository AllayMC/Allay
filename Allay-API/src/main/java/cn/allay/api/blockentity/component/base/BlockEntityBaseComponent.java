package cn.allay.api.blockentity.component.base;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.component.BlockEntityComponent;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.client.Client;
import cn.allay.api.component.annotation.Inject;
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

    @Inject
    BlockEntityType<? extends BlockEntity> getBlockEntityType();

    @Inject
    Position3ic getPosition();

    @Inject
    NbtMap saveNBT();

    @Inject
    void loadNBT(NbtMap nbt);

    @Inject
    default BlockEntityDataPacket createBlockEntityDataPacket() {
        var packet = new BlockEntityDataPacket();
        var pos = getPosition();
        packet.setBlockPosition(Vector3i.from(pos.x(), pos.y(), pos.z()));
        packet.setData(saveNBT());
        return packet;
    }

    @Inject
    default void sendBlockEntityDataPacketTo(Client client) {
        client.sendPacket(createBlockEntityDataPacket());
    }

    @Inject
    default void sendBlockEntityDataPacketToAll() {
        var pos = getPosition();
        var chunk = pos.world().getChunkService().getChunkByLevelPos(pos.x(), pos.z());
        Objects.requireNonNull(chunk, "The chunk located at pos " + pos + " is not loaded!");
        chunk.getClientChunkLoaders().forEach(this::sendBlockEntityDataPacketTo);
    }

    @Inject
    default void tick() {}

    @Inject
    default boolean sendToClient() {
        return true;
    }

    default BlockState getBlockState() {
        var pos = getPosition();
        return pos.world().getBlockState(pos.x(), pos.y(), pos.z());
    }
}
