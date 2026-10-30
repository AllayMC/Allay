package org.allaymc.server.world.storage.leveldb.codec;

import io.netty.buffer.ByteBufOutputStream;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.utils.AllayNBTUtils;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.World;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.storage.WorldStorageException;
import org.allaymc.server.world.storage.leveldb.LevelDBUtils;
import org.cloudburstmc.nbt.NbtUtils;
import org.jctools.maps.NonBlockingHashMap;

import java.io.IOException;
import java.util.Collection;

/**
 * {@code BlockEntityCodec} encodes
 * chunk block entities as consecutive little-endian NBT compounds and
 * reconstructs their position index. Empty collections produce a deletion
 * marker for the storage caller, while individual construction failures or
 * unknown types are omitted during loading. Each serialized compound retains
 * the block entity's absolute position for reconstruction in its dimension.
 *
 * <p>
 * The restored map uses chunk-local horizontal coordinates and absolute block
 * height, matching chunk lookup keys while leaving each entity's position
 * intact. Multiple compounds can resolve to the same key; the last successful
 * reconstruction replaces earlier entries. Construction recovery applies per
 * compound after the outer NBT sequence has been decoded, so malformed outer
 * data can still fail the complete load.
 */
@Slf4j
@UtilityClass
public final class BlockEntityCodec {

    /**
     * Serializes each block entity into a consecutive little-endian NBT
     * compound. An empty collection returns {@code null} so the storage caller deletes
     * an older record; I/O failures become {@link WorldStorageException}.
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
     * Reconstructs block entities using the requested world dimension and
     * indexes them by chunk-local horizontal coordinates and world height.
     * Individual construction failures and unsupported types are skipped;
     * duplicate position keys retain the last reconstructed entity. The codec
     * masks horizontal coordinates only for map indexing; entity positions
     * remain absolute within the requested dimension. Callers must supply the
     * record for the intended chunk because this method does not reject
     * compounds whose positions belong to another chunk. Malformed outer NBT
     * still fails before per-entity recovery can run.
     */
    public static NonBlockingHashMap<Integer, BlockEntity> deserialize(byte[] data, World world, DimensionType dimensionType) {
        var blockEntities = new NonBlockingHashMap<Integer, BlockEntity>();
        for (var nbt : AllayNBTUtils.bytesToNbtListLE(data)) {
            BlockEntity blockEntity;
            try {
                blockEntity = NBTIO.getAPI().fromBlockEntityNBT(world.getDimension(dimensionType), nbt);
            } catch (Throwable t) {
                // A failing block-entity constructor must not discard other
                // compounds in this chunk record; log this entry and continue
                // loading the remainder.
                log.error("Error while loading block entity from NBT", t);
                continue;
            }

            if (blockEntity == null) {
                // Unknown types have no reconstructible instance, so omit only this entry
                // while preserving other block entities decoded from the same record.
                continue;
            }

            var position = blockEntity.getPosition();
            // Horizontal masking converts world coordinates to the chunk-local
            // index expected by chunk block-entity lookups, while keeping the
            // full vertical coordinate.
            var key = HashUtils.hashChunkXYZ(position.x() & 15, position.y(), position.z() & 15);
            blockEntities.put(key, blockEntity);
        }

        return blockEntities;
    }
}
