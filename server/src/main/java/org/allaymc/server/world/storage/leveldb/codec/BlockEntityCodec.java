package org.allaymc.server.world.storage.leveldb.codec;

import io.netty.buffer.ByteBufOutputStream;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.utils.AllayNBTUtils;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.World;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.storage.WorldStorageException;
import org.cloudburstmc.nbt.NbtUtils;
import org.jctools.maps.NonBlockingHashMap;

import java.io.IOException;
import java.util.Collection;

/**
 * Codec for serializing/deserializing block entities.
 */
@Slf4j
@UtilityClass
public final class BlockEntityCodec {

    /**
     * Serialize block entities to bytes.
     *
     * @return the serialized bytes, or {@code null} if the collection is empty.
     */
    public static byte[] serialize(Collection<BlockEntity> blockEntities) {
        if (blockEntities.isEmpty()) {
            return null;
        }

        return LevelDBUtils.withByteBufToArray(buffer -> {
            try (var writerLE = NbtUtils.createWriterLE(new ByteBufOutputStream(buffer))) {
                for (BlockEntity blockEntity : blockEntities) {
                    writerLE.writeTag(blockEntity.saveNBT());
                }
            } catch (IOException e) {
                throw new WorldStorageException(e);
            }
        });
    }

    /**
     * Deserialize block entities from bytes.
     */
    public static NonBlockingHashMap<Integer, BlockEntity> deserialize(byte[] data, World world, DimensionInfo dimensionInfo) {
        var blockEntities = new NonBlockingHashMap<Integer, BlockEntity>();
        for (var nbt : AllayNBTUtils.bytesToNbtListLE(data)) {
            BlockEntity blockEntity;
            try {
                blockEntity = NBTIO.getAPI().fromBlockEntityNBT(world.getDimension(dimensionInfo.dimensionId()), nbt);
            } catch (Throwable t) {
                log.error("Error while loading block entity from NBT", t);
                continue;
            }

            if (blockEntity == null) {
                // blockEntity will be null if the entity type is unknown
                continue;
            }

            var position = blockEntity.getPosition();
            var key = HashUtils.hashChunkXYZ(position.x() & 15, position.y(), position.z() & 15);
            blockEntities.put(key, blockEntity);
        }

        return blockEntities;
    }
}
