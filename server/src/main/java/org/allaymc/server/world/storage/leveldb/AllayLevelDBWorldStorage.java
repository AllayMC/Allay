package org.allaymc.server.world.storage.leveldb;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayNBTUtils;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.api.world.storage.WorldStorageException;
import org.allaymc.server.world.AllayWorldData;
import org.allaymc.server.world.chunk.AllayChunkSection;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.storage.leveldb.codec.*;
import org.allaymc.server.world.storage.leveldb.data.ChunkVersion;
import org.allaymc.server.world.storage.leveldb.data.LevelDBKey;
import org.allaymc.server.world.storage.leveldb.data.VanillaChunkState;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.iq80.leveldb.CompressionType;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteBatch;
import org.iq80.leveldb.impl.Iq80DBFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@code AllayLevelDBWorldStorage} stores
 * Bedrock world metadata and chunk records in a directory containing
 * {@code level.dat} and a LevelDB database. Chunk sections, biome data, block entities,
 * pending updates, and points of interest use separate keys, while entity
 * digests connect each chunk to independently stored actor records. Special
 * thanks to the PMMP team for the format groundwork.
 *
 * <p>
 * A lock coordinates batch admission with flushing. Chunk requests retain their
 * chunk objects, and entity requests retain their maps until serialization;
 * callers must keep those objects stable during saving. Repeated batched entity
 * writes for one chunk replace the earlier request so the final digest does not
 * leave intermediate actors behind.
 *
 * <p>
 * Completion and recovery have implementation limitations: batched chunk
 * futures complete at admission, and asynchronous failures are logged and
 * converted to normal results. Metadata replacement and dirty-section
 * serialization are not rollback mechanisms. The individual methods describe
 * these limits rather than treating every successful return as confirmation of
 * persisted data.
 *
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class AllayLevelDBWorldStorage implements WorldStorage {

    /**
     * {@code FILE_LEVEL_DAT} names
     * the current metadata file written and read by this storage
     * implementation.
     */
    private static final String FILE_LEVEL_DAT = "level.dat";
    /**
     * {@code FILE_LEVEL_DAT_OLD} names
     * the metadata copy target used during replacement and initial world
     * creation.
     */
    private static final String FILE_LEVEL_DAT_OLD = "level.dat_old";

    /**
     * {@code DIR_DB} names the child directory opened as the LevelDB database for chunk
     * records.
     */
    private static final String DIR_DB = "db";

    /**
     * {@code CURRENT_CHUNK_VERSION} selects
     * the chunk-format ordinal stored in the version key during chunk
     * serialization.
     */
    private static final int CURRENT_CHUNK_VERSION = ChunkVersion.V1_21_120.ordinal();

    /**
     * Root world storage directory.
     */
    private final Path path;
    /**
     * {@code worldName} holds the final path component. Metadata creation uses
     * this value as the display name when saved metadata is absent.
     */
    private final String worldName;
    /**
     * {@code db} holds the database handle owned by this storage.
     * {@code shutdown()} closes it when the world explicitly requests cleanup.
     */
    private final DB db;

    // batchMode, batchChunks, and batchEntityRequests are coordinated by
    // batchLock. The same lock covers admission and draining so a request
    // cannot enter a batch after that batch has been flushed.
    /**
     * {@code batchChunks} retains chunk references until batch flushing
     * serializes them. Admission does not snapshot the chunks' mutable contents.
     */
    private final Queue<Chunk> batchChunks;
    /**
     * {@code batchEntityRequests} retains the last entity request per chunk and
     * dimension. Flushing serializes the retained shared map and its entities.
     */
    private final Map<EntityWriteKey, EntityWriteRequest> batchEntityRequests;
    /**
     * {@code batchLock} protects batch admission and flushing. The protected
     * state includes {@code batchMode}, {@code batchChunks}, and
     * {@code batchEntityRequests}.
     */
    private final Lock batchLock;
    /**
     * {@code batchMode} selects deferred chunk writes and synchronous entity
     * requests while a batch accumulates. Admission and flushing access this
     * flag under {@code batchLock}.
     */
    private boolean batchMode;

    /**
     * {@code world} holds the owning world. Lifecycle setup must assign it
     * before entity reconstruction or scheduled-update serialization needs its
     * dimensions or tick counter.
     */
    private World world;

    /**
     * Opens a world directory with database creation enabled, raw ZLIB
     * compression, and a 64 KiB block size. The resulting storage owns the
     * opened database until shutdown is called.
     */
    public AllayLevelDBWorldStorage(Path path) {
        this(path, new Options().createIfMissing(true).compressionType(CompressionType.ZLIB_RAW).blockSize(64 * 1024));
    }

    /**
     * Opens the world directory and its database using the supplied LevelDB
     * options. Missing directories are created, and directory creation or
     * database opening failures raise {@link WorldStorageException}; the path
     * must have a final name component.
     */
    public AllayLevelDBWorldStorage(Path path, Options options) {
        this.path = path;
        this.worldName = path.getName(path.getNameCount() - 1).toString();

        var file = path.toFile();
        if (!file.exists() && !file.mkdirs()) {
            throw new WorldStorageException("Failed to create world directory!");
        }

        var dbFolder = path.resolve(DIR_DB).toFile();
        try {
            if (!dbFolder.exists() && !dbFolder.mkdirs()) {
                throw new WorldStorageException("Failed to create world database directory!");
            }
            this.db = new Iq80DBFactory().open(dbFolder, options);
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }

        this.batchChunks = new ArrayDeque<>();
        this.batchEntityRequests = new LinkedHashMap<>();
        this.batchLock = new ReentrantLock();
        this.batchMode = false;
    }

    /**
     * Associates this storage with the world used when reconstructing entities
     * and serializing scheduled updates. A second assignment after retaining a
     * world other than {@code null} is rejected with an illegal state exception.
     */
    @Override
    public void setWorld(World world) {
        Preconditions.checkState(this.world == null, "World has already been set");
        this.world = world;
    }

    /**
     * Reads a chunk on the server virtual-thread executor. A failed read is
     * logged and replaced with a newly initialized chunk, so ordinary
     * completion does not distinguish missing data from recovered read failure.
     */
    @Override
    public CompletableFuture<Chunk> readChunk(int chunkX, int chunkZ, DimensionType dimensionType) {
        return CompletableFuture
                .supplyAsync(() -> readChunkSync(chunkX, chunkZ, dimensionType), Server.getInstance().getVirtualThreadPool())
                .exceptionally(t -> {
                    // A failed asynchronous read becomes a new chunk so the
                    // load pipeline can continue; the logged error remains the
                    // record of the lost read result.
                    log.error("Failed to read chunk ({}, {})", chunkX, chunkZ, t);
                    return AllayUnsafeChunk.builder().newChunk(chunkX, chunkZ, dimensionType).toSafeChunk();
                });
    }

    /**
     * Reconstructs a chunk from its records on the calling thread. A chunk
     * with no section records or a stored unfinished vanilla state yields a new chunk;
     * an absent or unrecognized Allay state is treated as full.
     *
     * <p>
     * Sections are decoded before biome data is applied because biome palettes
     * belong to those sections. Unsupported section versions become empty
     * sections through the fill step. Three-dimensional height and biome data
     * takes precedence over legacy two-dimensional data, and optional block-entity,
     * update, and point-of-interest records are loaded only when their keys
     * exist.
     */
    @Override
    public Chunk readChunkSync(int chunkX, int chunkZ, DimensionType dimensionType) {
        var builder = AllayUnsafeChunk.builder()
                .chunkX(chunkX)
                .chunkZ(chunkZ)
                .dimensionType(dimensionType)
                .state(ChunkState.NEW);
        if (!containChunk(chunkX, chunkZ, dimensionType)) {
            return builder.build().toSafeChunk();
        }

        var versionValue = this.db.get(LevelDBKey.VERSION.createKey(chunkX, chunkZ, dimensionType));
        if (versionValue == null) {
            // Older records may carry only the legacy discriminator, so check
            // that key before reporting a missing chunk-version field to the
            // operator.
            versionValue = this.db.get(LevelDBKey.LEGACY_VERSION.createKey(chunkX, chunkZ, dimensionType));
        }
        if (versionValue == null) {
            // The section decoder reads its own format version, so missing chunk-version
            // metadata is reported without preventing an attempt to decode the sections.
            log.warn("Chunk at ({}, {}) is missing version field", chunkX, chunkZ);
        }

        var chunkFinalizedState = this.db.get(LevelDBKey.CHUNK_FINALIZED_STATE.createKey(chunkX, chunkZ, dimensionType));
        if (chunkFinalizedState != null && Unpooled.wrappedBuffer(chunkFinalizedState).readByte() != VanillaChunkState.DONE.ordinal()) {
            // A present unfinished state must not expose partial stored terrain as full;
            // return a new chunk, while an absent state continues through legacy loading.
            return builder.build().toSafeChunk();
        }

        var chunkStateBytes = this.db.get(LevelDBKey.ALLAY_CHUNK_STATE.createKey(chunkX, chunkZ, dimensionType));
        if (chunkStateBytes != null) {
            var chunkStateName = new String(chunkStateBytes, StandardCharsets.UTF_8);
            try {
                builder.state(ChunkState.valueOf(chunkStateName));
            } catch (IllegalArgumentException e) {
                // An unknown Allay state cannot enter the generation-state
                // enum, so treat the stored terrain as full instead of
                // attempting an undefined generation transition.
                log.warn("Unknown chunk state {} for chunk at ({}, {})!", chunkStateName, chunkX, chunkZ);
                builder.state(ChunkState.FULL);
            }
        } else {
            // Worlds without the Allay-specific state tag still contain usable
            // section data, so the loader assigns the completed state for that
            // legacy path.
            builder.state(ChunkState.FULL);
        }

        // Sections
        var sections = new AllayChunkSection[dimensionType.chunkSectionCount()];
        var minSectionY = dimensionType.minSectionY();
        for (int sectionY = minSectionY; sectionY <= dimensionType.maxSectionY(); sectionY++) {
            var sectionData = this.db.get(LevelDBKey.CHUNK_SECTION_PREFIX.createKey(chunkX, chunkZ, sectionY, dimensionType));
            if (sectionData != null) {
                sections[sectionY - minSectionY] = ChunkSectionCodec.deserialize(sectionData, sectionY, chunkX, chunkZ);
            }
        }
        builder.sections(ChunkSectionCodec.fillNullSections(sections, dimensionType));

        // Height and biome
        var data3d = this.db.get(LevelDBKey.DATA_3D.createKey(chunkX, chunkZ, dimensionType));
        if (data3d != null) {
            HeightAndBiomeCodec.deserialize(data3d, builder);
        } else {
            // Only fall back to legacy column data when DATA_3D is absent; an
            // existing three-dimensional record remains authoritative for
            // height and biome reconstruction.
            var data2d = this.db.get(LevelDBKey.DATA_2D.createKey(chunkX, chunkZ, dimensionType));
            if (data2d != null) {
                HeightAndBiomeCodec.deserializeOld(data2d, builder);
            }
        }

        // Block entities
        var tileBytes = this.db.get(LevelDBKey.BLOCK_ENTITIES.createKey(chunkX, chunkZ, dimensionType));
        if (tileBytes != null) {
            builder.blockEntities(BlockEntityCodec.deserialize(tileBytes, world, dimensionType));
        }

        // Scheduled updates
        var scheduledBytes = this.db.get(LevelDBKey.PENDING_TICKS.createKey(chunkX, chunkZ, dimensionType));
        if (scheduledBytes != null) {
            builder.scheduledUpdates(ScheduledUpdateCodec.deserialize(scheduledBytes));
        }

        // POI data
        var poiBytes = this.db.get(LevelDBKey.ALLAY_POI_DATA.createKey(chunkX, chunkZ, dimensionType));
        if (poiBytes != null) {
            builder.poiEntries(PoiCodec.deserialize(poiBytes));
        }

        return builder.build().toSafeChunk();
    }

    /**
     * Enables deferred chunk writes and synchronous entity requests under the
     * batch lock. Repeated calls keep the same pending requests; they do not
     * create nested batches or flush an earlier batch.
     */
    @Override
    public void startBatchWrite() {
        batchLock.lock();
        try {
            batchMode = true;
        } finally {
            batchLock.unlock();
        }
    }

    /**
     * Serializes pending requests into one database write batch and exits batch
     * mode. The batch lock remains held while both request collections are
     * drained and the database write is attempted, excluding concurrent batch
     * admission.
     *
     * <p>
     * Requests retain live objects until this call serializes them. Chunk
     * requests are removed during serialization, and entity requests are
     * cleared before the database write; a failure does not restore those
     * requests or dirty flags already cleared by section serialization. Callers
     * must not interpret an earlier completed admission future as proof that
     * this flush succeeded.
     */
    @Override
    public void flushBatchWrite() {
        batchLock.lock();
        try {
            // Holding the admission lock while leaving batch mode and draining both
            // queues keeps later requests from being appended to the batch being
            // committed.
            batchMode = false;
            if (batchChunks.isEmpty() && batchEntityRequests.isEmpty()) return;
            try (var writeBatch = this.db.createWriteBatch()) {
                Chunk chunk;
                while ((chunk = batchChunks.poll()) != null) {
                    addChunkToBatch(chunk, writeBatch);
                }
                for (var req : batchEntityRequests.values()) {
                    addEntitiesToBatch(req.chunkX(), req.chunkZ(), req.dimensionType(), req.entities(), writeBatch);
                }
                // The replacement batch already contains entity bytes, so
                // queued references are discarded before submission; a database
                // failure cannot reconstruct those pending requests
                // automatically.
                batchEntityRequests.clear();
                this.db.write(writeBatch);
            } catch (IOException e) {
                throw new WorldStorageException(e);
            }
        } finally {
            batchLock.unlock();
        }
    }

    /**
     * Schedules a chunk write or admits its shared reference to the current
     * batch. Batch admission returns an already completed future before
     * serialization or database writing; this implementation therefore does not
     * fulfill the interface's completion-after-writing promise in that mode.
     *
     * <p>
     * Outside batch mode, the virtual-thread executor serializes the chunk, and
     * failures are logged and converted to normal completion. The future is
     * consequently not a reliable persistence-success signal. Callers must
     * keep the chunk stable until serialization has finished, or until the
     * enclosing batch has been flushed.
     */
    @Override
    public CompletableFuture<Void> writeChunk(Chunk chunk) {
        batchLock.lock();
        try {
            if (batchMode) {
                // Queue the live chunk under the same lock as the mode check,
                // preventing a flush from finishing between admission selection
                // and enqueueing.
                batchChunks.offer(chunk);
                return CompletableFuture.completedFuture(null);
            }
        } finally {
            batchLock.unlock();
        }
        return CompletableFuture
                .runAsync(() -> writeChunkSync(chunk), Server.getInstance().getVirtualThreadPool())
                .exceptionally(t -> {
                    // This recovery path logs a failed database operation but
                    // completes the future normally, so callers cannot use
                    // normal completion alone as a durability acknowledgement.
                    log.error("Failed to write chunk ({}, {})", chunk.getX(), chunk.getZ(), t);
                    return null;
                });
    }

    /**
     * Serializes and writes a chunk on the calling thread without joining the
     * deferred batch queue. Serialization clears written section dirty flags
     * before the database write, so a failed write does not restore those
     * flags.
     */
    @Override
    public void writeChunkSync(Chunk chunk) {
        try (var writeBatch = this.db.createWriteBatch()) {
            addChunkToBatch(chunk, writeBatch);
            this.db.write(writeBatch);
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    /**
     * Adds the chunk's format markers and serialized content to a caller-owned
     * write batch. The caller performs the database write and retains
     * responsibility for the batch lifetime; this method alone does not persist
     * anything.
     *
     * <p>
     * Only dirty block sections are encoded, while height and biome data is
     * always included. Encoding a section clears its dirty flags before the
     * database commit. Empty optional record collections add deletions so
     * previously stored block entities, pending updates, and points of interest
     * cannot reappear on the next load.
     */
    private void addChunkToBatch(Chunk chunk, WriteBatch writeBatch) {
        writeBatch.put(LevelDBKey.VERSION.createKey(chunk.getX(), chunk.getZ(), chunk.getDimensionType()), new byte[]{(byte) CURRENT_CHUNK_VERSION});
        writeBatch.put(
                LevelDBKey.CHUNK_FINALIZED_STATE.createKey(chunk.getX(), chunk.getZ(), chunk.getDimensionType()),
                LevelDBUtils.withByteBufToArray(buf -> buf.writeByte(VanillaChunkState.DONE.ordinal()))
        );
        writeBatch.put(
                LevelDBKey.ALLAY_CHUNK_STATE.createKey(chunk.getX(), chunk.getZ(), chunk.getDimensionType()),
                chunk.getState().name().getBytes()
        );
        // Read operations guard the chunk data while serializers inspect it;
        // section serialization also clears dirty flags before the enclosing
        // database batch is written.
        chunk.applyOperation(c -> {
            var allayUnsafeChunk = (AllayUnsafeChunk) c;
            var dimensionType = allayUnsafeChunk.getDimensionType();

            // Sections
            for (int ySection = dimensionType.minSectionY(); ySection <= dimensionType.maxSectionY(); ySection++) {
                var section = allayUnsafeChunk.getSection(ySection);
                // Clean sections retain their existing database records;
                // serialize only dirty block layers while the other chunk
                // records below are refreshed for this save.
                if (!section.hasDirtyBlockLayer()) {
                    continue;
                }
                writeBatch.put(
                        LevelDBKey.CHUNK_SECTION_PREFIX.createKey(allayUnsafeChunk.getX(), allayUnsafeChunk.getZ(), ySection, dimensionType),
                        ChunkSectionCodec.serialize(section, ySection)
                );
            }

            // Height and biome
            writeBatch.put(
                    LevelDBKey.DATA_3D.createKey(allayUnsafeChunk.getX(), allayUnsafeChunk.getZ(), dimensionType),
                    HeightAndBiomeCodec.serialize(allayUnsafeChunk)
            );

            // Block entities
            // Each optional codec uses null for an empty collection. Delete
            // that record in the same batch so an older saved collection cannot
            // return after reload.
            var blockEntitiesKey = LevelDBKey.BLOCK_ENTITIES.createKey(allayUnsafeChunk.getX(), allayUnsafeChunk.getZ(), dimensionType);
            var blockEntitiesData = BlockEntityCodec.serialize(allayUnsafeChunk.getBlockEntities().values());
            if (blockEntitiesData == null) {
                writeBatch.delete(blockEntitiesKey);
            } else {
                writeBatch.put(blockEntitiesKey, blockEntitiesData);
            }

            // Scheduled updates
            var scheduledKey = LevelDBKey.PENDING_TICKS.createKey(allayUnsafeChunk.getX(), allayUnsafeChunk.getZ(), dimensionType);
            var scheduledData = ScheduledUpdateCodec.serialize(allayUnsafeChunk.getScheduledUpdates().values(), world.getTick());
            if (scheduledData == null) {
                writeBatch.delete(scheduledKey);
            } else {
                writeBatch.put(scheduledKey, scheduledData);
            }

            // POI data
            var poiKey = LevelDBKey.ALLAY_POI_DATA.createKey(allayUnsafeChunk.getX(), allayUnsafeChunk.getZ(), dimensionType);
            var poiData = PoiCodec.serialize(allayUnsafeChunk.getPoiEntries());
            if (poiData == null) {
                writeBatch.delete(poiKey);
            } else {
                writeBatch.put(poiKey, poiData);
            }
        }, OperationType.READ, OperationType.READ);
    }

    /**
     * Replaces a chunk's entities synchronously unless batch mode is active. In
     * batch mode the latest request for these chunk coordinates and dimension
     * replaces the previous shared map reference, and persistence waits for
     * flushing.
     *
     * <p>
     * The pending request key includes the dimension identifier, so identical
     * chunk coordinates in different dimensions remain separate replacements.
     * Keeping only the latest request prevents intermediate actor writes whose
     * records could survive after the final digest stops referencing them.
     * Replaced requests are discarded without serialization, and callers keep
     * the retained map and entities stable until flushing.
     */
    @Override
    public void writeEntitiesSync(int chunkX, int chunkZ, DimensionType dimensionType, Map<Long, Entity> entities) {
        batchLock.lock();
        try {
            if (batchMode) {
                // Keep only the last replacement for a chunk and dimension;
                // serializing intermediate replacements against the same old
                // digest could leave their actor records unreferenced.
                batchEntityRequests.put(
                        new EntityWriteKey(chunkX, chunkZ, dimensionType.getId()),
                        new EntityWriteRequest(chunkX, chunkZ, dimensionType, entities)
                );
                return;
            }
        } finally {
            batchLock.unlock();
        }
        writeEntities0(chunkX, chunkZ, dimensionType, entities, false);
    }

    /**
     * Adds an entity replacement and its digest to a caller-owned write batch.
     * Supplied map keys are persistent entity identifiers, and the entity
     * objects are serialized during this call.
     *
     * <p>
     * The previous digest identifies actor records to delete before replacement
     * records and the new digest are added to the same batch. An empty map
     * therefore removes prior actors while retaining an empty digest. The
     * caller must submit this batch, and must avoid competing replacements for
     * the same chunk outside the batch admission protocol.
     */
    private void addEntitiesToBatch(int chunkX, int chunkZ, DimensionType dimensionType, Map<Long, Entity> entities, WriteBatch writeBatch) {
        var idsBuf = ByteBufAllocator.DEFAULT.buffer();
        try {
            var idsKey = LevelDBKey.createEntityIdsKey(chunkX, chunkZ, dimensionType);

            // Remove actors named by the old digest before adding replacement actors and
            // their digest to this same batch, keeping the stored index and records aligned.
            var oldIds = this.db.get(idsKey);
            if (oldIds != null) {
                var oldIdsBuf = Unpooled.wrappedBuffer(oldIds);
                for (int i = 0; i < oldIds.length; i += Long.BYTES) {
                    writeBatch.delete(LevelDBKey.indexEntity(oldIdsBuf.readLongLE()));
                }
            }

            for (var entry : entities.entrySet()) {
                idsBuf.writeLongLE(entry.getKey());
                writeBatch.put(LevelDBKey.indexEntity(entry.getKey()), AllayNBTUtils.nbtToBytesLE(entry.getValue().saveNBT()));
            }

            writeBatch.put(idsKey, ByteBufUtil.getBytes(idsBuf));
        } finally {
            // The digest bytes have been copied into the write batch or
            // serialization has failed; release the temporary buffer without
            // retaining its storage.
            idsBuf.release();
        }
    }

    /**
     * Reads stored entities on the server virtual-thread executor. A failure is
     * logged and converted to an empty map, so an empty successful result does
     * not establish that the chunk contained no entities.
     */
    @Override
    public CompletableFuture<Map<Long, Entity>> readEntities(int chunkX, int chunkZ, DimensionType dimensionType) {
        return CompletableFuture
                .supplyAsync(() -> readEntitiesSync(chunkX, chunkZ, dimensionType), Server.getInstance().getVirtualThreadPool())
                .exceptionally(t -> {
                    // A failed entity read becomes an empty map so the caller
                    // receives a usable collection; the logged error
                    // distinguishes this recovery from an empty stored digest.
                    log.error("Failed to read entities in chunk ({}, {})", chunkX, chunkZ, t);
                    return Collections.emptyMap();
                });
    }

    /**
     * Reconstructs entities referenced by the chunk's actor digest on the
     * calling thread. An absent digest selects the legacy concatenated-NBT
     * representation, whereas an empty digest represents an explicitly empty
     * actor set.
     *
     * <p>
     * Missing actor records and unsupported entity types are skipped
     * individually. Returned keys come from each reconstructed entity's unique
     * identifier, rather than blindly reusing the digest entry, so the result
     * follows the identifier in entity NBT. Other decoding failures propagate
     * to the caller; the asynchronous wrapper applies its own empty-result
     * recovery.
     */
    @Override
    public Map<Long, Entity> readEntitiesSync(int chunkX, int chunkZ, DimensionType dimensionType) {
        var ids = this.db.get(LevelDBKey.createEntityIdsKey(chunkX, chunkZ, dimensionType));
        if (ids == null) {
            // Only an absent digest selects legacy storage; an existing empty digest
            // must remain empty instead of resurrecting entities from an older record.
            return readEntitiesOldSync(chunkX, chunkZ, dimensionType);
        }

        var map = new Long2ObjectOpenHashMap<Entity>();
        var idsBuf = Unpooled.wrappedBuffer(ids);
        for (var i = 0; i < ids.length; i += Long.BYTES) {
            var id = idsBuf.readLongLE();
            var nbt = this.db.get(LevelDBKey.indexEntity(id));
            if (nbt == null) {
                // A digest can reference missing actor data; report that broken
                // reference and continue with other actors rather than
                // discarding every entity in the chunk.
                log.error("NBT data for existing entity unique id {} is missing!", id);
                continue;
            }

            var entity = NBTIO.getAPI().fromEntityNBT(world.getDimension(dimensionType), AllayNBTUtils.bytesToNbtLE(nbt));
            if (entity == null) {
                // Unsupported entity NBT has no runtime instance; omit that actor while
                // retaining other entities reconstructed from the same stored chunk.
                log.error("Failed to load entity from NBT {} in chunk ({}, {})", nbt, chunkX, chunkZ);
                continue;
            }

            map.put(entity.getUniqueId().getLeastSignificantBits(), entity);
        }

        return map;
    }

    /**
     * Loads the legacy concatenated entity NBT record when no actor digest
     * exists. Missing legacy data returns an empty map; unsupported entity
     * types are logged and skipped while other decoded entities remain
     * available.
     */
    protected Map<Long, Entity> readEntitiesOldSync(int chunkX, int chunkZ, DimensionType dimensionType) {
        var entityBytes = db.get(LevelDBKey.ENTITIES.createKey(chunkX, chunkZ, dimensionType));
        if (entityBytes == null) {
            return Collections.emptyMap();
        }

        var map = new Long2ObjectOpenHashMap<Entity>();
        for (var nbt : AllayNBTUtils.bytesToNbtListLE(entityBytes)) {
            var entity = NBTIO.getAPI().fromEntityNBT(world.getDimension(dimensionType), nbt);
            if (entity == null) {
                // Unsupported entity NBT has no runtime instance; omit that actor while
                // retaining other entities reconstructed from the same stored chunk.
                log.error("Failed to load entity from NBT {} in chunk ({}, {})", nbt, chunkX, chunkZ);
                continue;
            }

            map.put(entity.getUniqueId().getLeastSignificantBits(), entity);
        }

        return map;
    }

    /**
     * Serializes an entity replacement immediately and submits its database
     * write asynchronously, without using the deferred batch queue.
     * Serialization failures can escape directly; asynchronous write failures
     * are logged and converted to normal completion.
     */
    @Override
    public CompletableFuture<Void> writeEntities(int chunkX, int chunkZ, DimensionType dimensionType, Map<Long, Entity> entities) {
        return writeEntities0(chunkX, chunkZ, dimensionType, entities, true);
    }

    /**
     * Builds a complete actor replacement using the supplied persistent
     * identifier mapping. Entity NBT is captured on the calling thread before
     * the optional asynchronous database write begins.
     *
     * <p>
     * The old digest supplies actor keys to delete, and the replacement actor
     * records and digest share one write batch. The batch is closed when this
     * method returns, including after asynchronous submission. The configured
     * HiveMC LevelDB 1.1.0 batch implementation has a no-op {@code close()}, so
     * that close does not invalidate the submitted records.
     * No lock here serializes competing replacements of the same chunk; callers
     * must provide that ordering.
     */
    protected CompletableFuture<Void> writeEntities0(int chunkX, int chunkZ, DimensionType dimensionType, Map<Long, Entity> entities, boolean asyncWrite) {
        var idsBuf = ByteBufAllocator.DEFAULT.buffer();
        try (var writeBatch = this.db.createWriteBatch()) {
            var idsKey = LevelDBKey.createEntityIdsKey(chunkX, chunkZ, dimensionType);

            // Remove actors named by the old digest before adding replacement actors and
            // their digest to this same batch, keeping the stored index and records aligned.
            var oldIds = this.db.get(idsKey);
            if (oldIds != null) {
                var oldIdsBuf = Unpooled.wrappedBuffer(oldIds);
                for (var i = 0; i < oldIds.length; i += Long.BYTES) {
                    writeBatch.delete(LevelDBKey.indexEntity(oldIdsBuf.readLongLE()));
                }
            }

            for (var entry : entities.entrySet()) {
                var entity = entry.getValue();
                idsBuf.writeLongLE(entry.getKey());
                writeBatch.put(LevelDBKey.indexEntity(entry.getKey()), AllayNBTUtils.nbtToBytesLE(entity.saveNBT()));
            }

            writeBatch.put(idsKey, ByteBufUtil.getBytes(idsBuf));
            // Entity NBT and digest bytes are already captured before
            // submission. The configured LevelDB batch close is a no-op,
            // preserving these records for asynchronous consumption.
            return handleEntitiesWriteBatch(chunkX, chunkZ, writeBatch, asyncWrite);
        } catch (IOException e) {
            throw new WorldStorageException(e);
        } finally {
            // The batch owns a copied digest array, so the temporary buffer can be
            // released even while an asynchronously submitted database write is pending.
            idsBuf.release();
        }
    }

    /**
     * Writes the prepared batch directly or submits it to the virtual-thread
     * executor. This method does not own the batch lifetime; asynchronous
     * failures are logged and converted to normal completion, while synchronous
     * failures propagate.
     */
    protected CompletableFuture<Void> handleEntitiesWriteBatch(int chunkX, int chunkZ, WriteBatch writeBatch, boolean asyncWrite) {
        if (asyncWrite) {
            return CompletableFuture
                    .runAsync(() -> this.db.write(writeBatch), Server.getInstance().getVirtualThreadPool())
                    .exceptionally(t -> {
                        // This asynchronous failure is recorded only in the log
                        // and converted to normal completion, so the returned
                        // future does not prove that entity persistence
                        // succeeded.
                        log.error("Failed to write entities in chunk ({}, {})", chunkX, chunkZ, t);
                        return null;
                    });
        }

        this.db.write(writeBatch);
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Returns whether any section key exists within the requested dimension's
     * section range. Metadata and entity records alone do not count as a stored
     * chunk, and section bytes are not validated here.
     */
    @Override
    public boolean containChunk(int x, int z, DimensionType dimensionType) {
        for (int ySection = dimensionType.minSectionY(); ySection <= dimensionType.maxSectionY(); ySection++) {
            var bytes = db.get(LevelDBKey.CHUNK_SECTION_PREFIX.createKey(x, z, ySection, dimensionType));
            if (bytes != null) {
                return true;
            }
        }

        return false;
    }

    /**
     * Writes Allay world metadata with an eight-byte little-endian header
     * followed by NBT. The argument must be an {@link AllayWorldData}; I/O
     * failures become {@link WorldStorageException}.
     *
     * <p>
     * Opening the output stream truncates {@code level.dat} before the later copy to
     * {@code level.dat_old} occurs. That copy therefore does not preserve the previous
     * metadata contents, and a later write failure can leave partial current
     * data without a usable old copy. This method does not provide atomic
     * replacement or rollback, even though it writes a file named as an older
     * version.
     */
    @Override
    public void writeWorldData(WorldData worldData) {
        var allayWorldData = (AllayWorldData) worldData;
        var levelDat = path.resolve(FILE_LEVEL_DAT).toFile();
        // Opening this output stream truncates the current file before the copy
        // below; the old-file copy therefore cannot serve as rollback of the
        // preceding metadata.
        try (var output = new FileOutputStream(levelDat);
             var byteArrayOutputStream = new ByteArrayOutputStream();
             var nbtOutputStream = NbtUtils.createWriterLE(byteArrayOutputStream)) {
            if (levelDat.exists()) {
                Files.copy(path.resolve(FILE_LEVEL_DAT), path.resolve(FILE_LEVEL_DAT_OLD), StandardCopyOption.REPLACE_EXISTING);
            }

            // The header stores a four-byte format version followed by the NBT byte
            // length, allowing the payload to begin after the fixed eight-byte prefix.
            output.write(LevelDBUtils.int2ByteArrayLE(WorldDataCodec.CURRENT_STORAGE_VERSION));

            nbtOutputStream.writeTag(WorldDataCodec.writeWorldDataToNBT(allayWorldData));
            var data = byteArrayOutputStream.toByteArray();

            output.write(LevelDBUtils.int2ByteArrayLE(data.length));

            output.write(data);
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    /**
     * Reads {@code level.dat} after skipping its eight-byte header, or creates default
     * metadata when the file is absent. Existing malformed files are not
     * recovered from {@code level.dat_old}; I/O failures become {@link
     * WorldStorageException}.
     */
    @Override
    public WorldData readWorldData() {
        var levelDat = path.resolve(FILE_LEVEL_DAT).toFile();
        if (!levelDat.exists()) {
            return createWorldData(worldName);
        }

        try (var input = new FileInputStream(levelDat)) {
            // Skip the two four-byte header fields to reach the little-endian NBT
            // payload; this reader does not validate their values or the declared length.
            // noinspection ResultOfMethodCallIgnored
            input.skip(8);
            try (NBTInputStream readerLE = NbtUtils.createReaderLE(new ByteArrayInputStream(input.readAllBytes()))) {
                NbtMap nbt = (NbtMap) readerLE.readTag();
                return WorldDataCodec.readWorldDataFromNBT(nbt);
            }
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    /**
     * Returns the registry name identifying the LevelDB world storage
     * implementation.
     */
    @Override
    public String getName() {
        return "LEVELDB";
    }

    /**
     * Creates default Allay metadata using the supplied world display name. The
     * method creates {@code level.dat}, writes the new metadata through the ordinary
     * writer, and then copies that completed file to {@code level.dat_old}.
     *
     * <p>
     * The final copy gives a newly created world matching current and old
     * metadata files after successful completion. Failures at any earlier step
     * are reported as storage exceptions without deleting or restoring files,
     * so a failed first creation can leave an empty or partial file that later
     * reads encounter.
     */
    private WorldData createWorldData(String worldName) {
        var levelDat = path.resolve(FILE_LEVEL_DAT).toFile();
        try {
            // noinspection ResultOfMethodCallIgnored
            levelDat.createNewFile();
            var worldData = AllayWorldData
                    .builder()
                    .displayName(worldName)
                    .build();
            // Initialize the current metadata first, then copy that completed
            // file to the old-file path so a successful new-world creation
            // leaves matching metadata files.
            writeWorldData(worldData);
            Files.copy(levelDat.toPath(), path.resolve(FILE_LEVEL_DAT_OLD), StandardCopyOption.REPLACE_EXISTING);
            return worldData;
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    /**
     * Closes the owned database handle and converts closing I/O failures to
     * storage exceptions. This method does not flush deferred requests or wait
     * for outstanding asynchronous writes; callers must finish those operations
     * beforehand.
     */
    @Override
    public void shutdown() {
        try {
            this.db.close();
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    /**
     * {@code EntityWriteKey} identifies one chunk and dimension for deduplicating
     * pending entity
     * replacements.
     *
     * @param chunkX chunk X coordinate
     * @param chunkZ chunk Z coordinate
     * @param dimensionId numeric dimension identifier
     */
    private record EntityWriteKey(int chunkX, int chunkZ, int dimensionId) {}

    /**
     * {@code EntityWriteRequest} retains a pending entity replacement until
     * batch flushing serializes its
     * contents.
     *
     * @param chunkX chunk X coordinate
     * @param chunkZ chunk Z coordinate
     * @param dimensionType target dimension definition
     * @param entities {@code entities} retains the shared entity mapping without
     *                 copying. Callers keep the mapping and its entities stable
     *                 until flushing serializes them
     */
    private record EntityWriteRequest(int chunkX, int chunkZ, DimensionType dimensionType, Map<Long, Entity> entities) {}
}
