package org.allaymc.server.world.storage.leveldb.codec;

import io.netty.buffer.ByteBufOutputStream;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.utils.AllayNBTUtils;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.storage.WorldStorageException;
import org.allaymc.server.world.chunk.ScheduledUpdateInfo;
import org.allaymc.server.world.storage.leveldb.LevelDBUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;
import org.jctools.maps.NonBlockingHashMap;

import java.io.IOException;
import java.util.Collection;

/**
 * Codec for serializing/deserializing scheduled (pending) tick updates.
 */
@Slf4j
@UtilityClass
public final class ScheduledUpdateCodec {

    private static final String TAG_CURRENT_TICK = "currentTick";
    private static final String TAG_TICK_LIST = "tickList";

    /**
     * Serialize scheduled updates to bytes.
     *
     * @return the serialized bytes, or {@code null} if the collection is empty.
     */
    public static byte[] serialize(Collection<ScheduledUpdateInfo> scheduledUpdates, long worldTick) {
        if (scheduledUpdates.isEmpty()) {
            return null;
        }

        return LevelDBUtils.withByteBufToArray(buffer -> {
            try (var writerLE = NbtUtils.createWriterLE(new ByteBufOutputStream(buffer))) {
                var nbt = NbtMap.builder()
                        .putInt(TAG_CURRENT_TICK, (int) worldTick)
                        .putList(TAG_TICK_LIST, NbtType.COMPOUND, scheduledUpdates.stream().map(ScheduledUpdateInfo::toNBT).toList())
                        .build();
                writerLE.writeTag(nbt);
            } catch (IOException e) {
                throw new WorldStorageException(e);
            }
        });
    }

    /**
     * Deserialize scheduled updates from bytes.
     */
    public static NonBlockingHashMap<Integer, ScheduledUpdateInfo> deserialize(byte[] data) {
        var nbt = AllayNBTUtils.bytesToNbtLE(data);
        var tickList = nbt.getList(TAG_TICK_LIST, NbtType.COMPOUND);
        var scheduledUpdates = new NonBlockingHashMap<Integer, ScheduledUpdateInfo>(tickList.size());
        for (var entry : tickList) {
            var info = ScheduledUpdateInfo.fromNBT(entry);
            var pos = info.getPos();
            scheduledUpdates.put(HashUtils.hashChunkXYZ(pos.x() & 15, pos.y(), pos.z() & 15), info);
        }

        return scheduledUpdates;
    }
}
