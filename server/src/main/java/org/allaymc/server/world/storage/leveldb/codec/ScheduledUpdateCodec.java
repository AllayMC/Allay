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
 * {@code ScheduledUpdateCodec} encodes
 * pending block updates as little-endian NBT and restores their
 * chunk-position index. Update times remain the absolute ticks stored by each
 * update; the separately written current-tick field is metadata and is not used
 * to rebase times during decoding.
 *
 * <p>
 * Each update descriptor carries a dimension position, whereas chunk lookups
 * use local horizontal coordinates in their position hashes. Decoding masks
 * only the map key and retains the descriptor's absolute position. Scheduled
 * execution and later serialization therefore still receive the chunk origin,
 * while lookups address the same local position as the chunk scheduling API.
 */
@Slf4j
@UtilityClass
public final class ScheduledUpdateCodec {

    /**
     * {@code TAG_CURRENT_TICK} names
     * the saved world-tick metadata field; decoding update entries does
     * not consume this field.
     */
    private static final String TAG_CURRENT_TICK = "currentTick";
    /**
     * {@code TAG_TICK_LIST} names
     * the compound-list field holding each pending update's position and
     * absolute execution tick.
     */
    private static final String TAG_TICK_LIST = "tickList";

    /**
     * Serializes pending update records with the current world tick as
     * metadata. Empty input returns {@code null} for deletion of an older storage
     * record; individual update times are serialized unchanged by their NBT
     * conversion.
     */
    public static byte[] serialize(Collection<ScheduledUpdateInfo> scheduledUpdates, long worldTick) {
        if (scheduledUpdates.isEmpty()) {
            return null;
        }

        return LevelDBUtils.withByteBufToArray(buffer -> {
            try (var writerLE = NbtUtils.createWriterLE(new ByteBufOutputStream(buffer))) {
                // The currentTick field stores only the low 32 bits of the
                // world counter; each update retains its separate long
                // execution time.
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
     * Restores scheduled updates indexed by chunk-local horizontal coordinates
     * and world height. The saved current-tick metadata is ignored, and later
     * entries replace earlier entries with the same position hash. Horizontal
     * masking affects only the map key; each descriptor retains the absolute
     * dimension position loaded from NBT for execution and later serialization.
     * The caller supplies records for one chunk because different absolute
     * positions sharing a local hash otherwise overwrite one another in this
     * returned mapping.
     */
    public static NonBlockingHashMap<Integer, ScheduledUpdateInfo> deserialize(byte[] data) {
        var nbt = AllayNBTUtils.bytesToNbtLE(data);
        var tickList = nbt.getList(TAG_TICK_LIST, NbtType.COMPOUND);
        var scheduledUpdates = new NonBlockingHashMap<Integer, ScheduledUpdateInfo>(tickList.size());
        for (var entry : tickList) {
            var info = ScheduledUpdateInfo.fromNBT(entry);
            var pos = info.getPos();
            // Chunk lookups use local X and Z with absolute Y, so normalize the
            // index while leaving each update's stored world position intact.
            scheduledUpdates.put(HashUtils.hashChunkXYZ(pos.x() & 15, pos.y(), pos.z() & 15), info);
        }

        return scheduledUpdates;
    }
}
