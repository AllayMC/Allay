package org.allaymc.server.world.manager;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkLoader;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.manager.ChunkManager;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.AllayServer;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.chunk.ChunkHolder;
import org.jctools.maps.NonBlockingHashMapLong;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

/**
 * @author daoge_cmd
 */
@Slf4j
public final class AllayChunkManager implements ChunkManager {

    @Getter
    private final Dimension dimension;
    @Getter
    private final WorldGenerator worldGenerator;
    @Getter
    private final WorldStorage worldStorage;
    private final Map<Long, ChunkHolder> chunkHolders;
    private final Map<ChunkLoader, ChunkLoaderHolder> chunkLoaders;

    public AllayChunkManager(Dimension dimension, WorldGenerator worldGenerator, WorldStorage worldStorage) {
        this.dimension = dimension;
        this.worldGenerator = worldGenerator;
        this.worldStorage = worldStorage;
        this.chunkHolders = new NonBlockingHashMapLong<>();
        this.chunkLoaders = new Object2ObjectOpenHashMap<>();
    }

    public void shutdown() {
        tickAndWait(unloadAllChunks());
    }

    private void tickAndWait(CompletableFuture<?> future) {
        // Tick the chunk holders to trigger the unload logic
        tickChunkHolders();
        try {
            future.get(1, TimeUnit.SECONDS);
        } catch (TimeoutException $) {
            // Tick the chunk holders again since there may be locked chunks during the last tick
            tickAndWait(future);
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error while waiting for chunk unload future!", e);
        }
    }

    public void tick(long currentTick) {
        tickChunkLoaders();
        tickChunkHolders();
        tickChunks(currentTick);
    }

    private void tickChunkLoaders() {
        // NOTICE: There is no need to use parallel stream here
        for (var chunkLoaderHolder : chunkLoaders.values()) {
            try {
                chunkLoaderHolder.tick();
            } catch (Throwable t) {
                log.error("Error while ticking chunk loader {}!", chunkLoaderHolder.getChunkLoader(), t);
            }
        }
    }

    private void tickChunkHolders() {
        this.chunkHolders.values().forEach(ChunkHolder::tick);
    }

    private void tickChunks(long currentTick) {
        forEachLoadedChunks(chunk -> {
            if (!shouldTickChunk(chunk)) {
                return;
            }

            try {
                chunk.applyOperation(unsafeChunk -> ((AllayUnsafeChunk) unsafeChunk).tick(currentTick, dimension), OperationType.WRITE, OperationType.WRITE);
                ((AllayUnsafeChunk) chunk.toUnsafeChunk()).checkAutoSave(worldStorage);
            } catch (Throwable t) {
                log.error("Error while ticking chunk({}, {})!", chunk.getX(), chunk.getZ(), t);
            }
        });
    }

    private boolean shouldTickChunk(Chunk chunk) {
        var cx = chunk.getX();
        var cz = chunk.getZ();
        var shouldTick = false;
        for (var chunkLoader : chunk.getChunkLoaders()) {
            var lcx = ((int) Math.floor(chunkLoader.getLocation().x())) >> 4;
            var lcz = ((int) Math.floor(chunkLoader.getLocation().z())) >> 4;
            if (Math.pow(lcx - cx, 2) + Math.pow(lcz - cz, 2) <= Math.pow(AllayServer.getSettings().worldSettings().tickRadius(), 2)) {
                shouldTick = true;
                break;
            }
        }
        return shouldTick;
    }

    @Override
    public void removeUnusedChunksImmediately() {
        this.chunkHolders.values().forEach(chunkHolder -> {
            if (!chunkHolder.isUsed()) {
                chunkHolder.setRemoveCountDown(0);
            }
        });
    }

    public ChunkHolder getOrCreateChunkHolder(int x, int z) {
        return this.chunkHolders.computeIfAbsent(HashUtils.hashXZ(x, z), $ -> new ChunkHolder(this, x, z, worldStorage.readChunk(x, z, dimension.getDimensionInfo())));
    }

    public ChunkHolder getChunkHolder(int x, int z) {
        return this.chunkHolders.get(HashUtils.hashXZ(x, z));
    }

    public void removeChunkHolder(int x, int z) {
        var chunkHolder = this.chunkHolders.remove(HashUtils.hashXZ(x, z));
        if (chunkHolder == null) {
            log.warn("Trying to remove a chunk holder ({}, {}) that is not loaded!", x, z);
        }
    }

    @Override
    public CompletableFuture<Chunk> loadChunk(int x, int z) {
        var chunkHolder = getOrCreateChunkHolder(x, z);
        chunkHolder.setTargetState(ChunkState.FULL);
        return chunkHolder.getLoadFuture();
    }

    @Override
    public CompletableFuture<Void> unloadChunk(int x, int z) {
        var chunkHolder = getChunkHolder(x, z);
        if (chunkHolder == null) {
            log.warn("Trying to unload a chunk ({}, {}) that is not loaded!", x, z);
            return CompletableFuture.completedFuture(null);
        }

        chunkHolder.setRemoveCountDown(0);
        return chunkHolder.getUnloadFuture();
    }

    @Override
    public CompletableFuture<Chunk> getChunkFuture(int x, int z) {
        var chunkHolder = getChunkHolder(x, z);
        return chunkHolder != null ? chunkHolder.getLoadFuture() : null;
    }

    @Override
    public Chunk getChunk(int x, int z) {
        var chunkHolder = getChunkHolder(x, z);
        if (chunkHolder == null || !chunkHolder.isValidFullChunk()) {
            return null;
        }

        return chunkHolder.getChunk();
    }

    @Override
    public CompletableFuture<Chunk> getOrLoadChunk(int x, int z) {
        var chunkHolder = getOrCreateChunkHolder(x, z);
        chunkHolder.setTargetState(ChunkState.FULL);
        return chunkHolder.getLoadFuture();
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        var chunkHolder = getChunkHolder(x, z);
        if (chunkHolder == null) {
            return false;
        }

        return chunkHolder.isValidFullChunk();
    }

    @Override
    public boolean isChunkLoading(int x, int z) {
        var chunkHolder = getChunkHolder(x, z);
        if (chunkHolder == null) {
            return false;
        }

        return !chunkHolder.isValidFullChunk();
    }

    @Override
    public boolean isChunkUnloaded(int x, int z) {
        return getChunkHolder(x, z) == null;
    }

    @Override
    @UnmodifiableView
    public Set<ChunkLoader> getChunkLoaders() {
        return Collections.unmodifiableSet(chunkLoaders.keySet());
    }

    @Override
    public void addChunkLoader(ChunkLoader chunkLoader) {
        var old = this.chunkLoaders.put(chunkLoader, new ChunkLoaderHolder(this, chunkLoader));
        if (old != null) old.onRemoved();
    }

    @Override
    public void removeChunkLoader(ChunkLoader chunkLoader) {
        var removed = this.chunkLoaders.remove(chunkLoader);
        if (removed != null) removed.onRemoved();
    }

    @Override
    public void forEachLoadedChunks(Consumer<Chunk> consumer) {
        for (var chunkHolder : chunkHolders.values()) {
            if (chunkHolder.isValidFullChunk()) {
                consumer.accept(chunkHolder.getChunk());
            }
        }
    }

    @Override
    @UnmodifiableView
    public Collection<Chunk> getLoadedChunks() {
        return chunkHolders.values().stream().filter(ChunkHolder::isValidFullChunk).map(ChunkHolder::getChunk).toList();
    }

    @Override
    public CompletableFuture<Void> unloadAllChunks() {
        return CompletableFuture.allOf(chunkHolders.keySet().stream().map(this::unloadChunk).toArray(CompletableFuture[]::new));
    }
}
