package org.allaymc.server.world.generator;

import com.google.common.base.Preconditions;
import io.netty.util.internal.PlatformDependent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkSource;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.generator.WorldGeneratorType;
import org.allaymc.api.world.generator.context.EntitySpawnContext;
import org.allaymc.api.world.generator.context.NoiseContext;
import org.allaymc.api.world.generator.context.PopulateContext;
import org.allaymc.api.world.generator.function.EntitySpawner;
import org.allaymc.api.world.generator.function.Noiser;
import org.allaymc.api.world.generator.function.Populator;
import org.allaymc.server.AllayServer;
import org.allaymc.server.datastruct.collections.queue.BlockingQueueWrapper;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.jctools.maps.NonBlockingHashMapLong;
import org.jctools.maps.NonBlockingHashSet;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayWorldGenerator implements WorldGenerator {

    @Getter
    private final String name;
    @Getter
    private final WorldGeneratorType type;
    @Getter
    private final String preset;
    private final List<Noiser> noisers;
    private final List<Populator> populators;
    private final List<EntitySpawner> entitySpawners;
    private final Consumer<Dimension> onDimensionSet;
    private final Map<Long, CompletableFuture<Chunk>> chunkNoiseFutures = new NonBlockingHashMapLong<>();
    private final Map<Long, CompletableFuture<Chunk>> chunkFutures = new NonBlockingHashMapLong<>();
    private final BlockingQueueWrapper<PopulationQueueEntry> populationQueue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
    private final Set<Long> populationLocks = new NonBlockingHashSet<>();
    private final ExecutorService computeThreadPool = AllayServer.getInstance().getComputeThreadPool();

    @Getter
    private Dimension dimension; // Will be set later

    private AllayWorldGenerator(
            String name,
            WorldGeneratorType type,
            String preset,
            List<Noiser> noisers,
            List<Populator> populators,
            List<EntitySpawner> entitySpawners,
            Consumer<Dimension> onDimensionSet
    ) {
        this.name = name;
        this.type = type;
        this.preset = preset;
        this.noisers = noisers;
        this.populators = populators;
        this.entitySpawners = entitySpawners;
        this.onDimensionSet = onDimensionSet;
        this.noisers.forEach(noiser -> noiser.init(this));
        this.populators.forEach(populator -> populator.init(this));
        this.entitySpawners.forEach(entitySpawner -> entitySpawner.init(this));
    }

    public static WorldGeneratorBuilder builder() {
        return new AllayWorldGeneratorBuilder();
    }

    @Override
    public void setDimension(Dimension dimension) {
        if (this.dimension != null) {
            throw new IllegalStateException("Dimension already set");
        }
        this.dimension = dimension;
        this.onDimensionSet.accept(dimension);
    }

    public void startTick() {
        Thread.ofPlatform().name("Population Queue Processing Thread - " + dimension.getWorld().getWorldData().getDisplayName()).start(() -> {
            while (dimension.getWorld().isRunning()) {
                processPopulationQueue();
            }
        });
    }

    private void processPopulationQueue() {
        // dimension may be null in a short term when initializing
        if (dimension != null && !dimension.getWorld().isRunning()) {
            return;
        }

        PopulationQueueEntry entry;
        while ((entry = populationQueue.tryPoll(1, TimeUnit.SECONDS)) != null) {
            var noiseFuture = entry.noiseFuture();
            var chunk = noiseFuture.getNow(null);
            if (chunk == null) {
                // Noise not generated, re-add to queue
                populationQueue.offer(entry);
                continue;
            }

            var canPopulate = tryEnterPopulationStage(chunk.getX(), chunk.getZ());
            if (!canPopulate) {
                // This chunk or adjacent chunk is locked, re-add to queue
                populationQueue.offer(entry);
                continue;
            }

            var chunkFuture = entry.chunkFuture();
            var chunkHash = HashUtils.hashXZ(chunk.getX(), chunk.getZ());
            noiseFuture.thenAcceptAsync($ -> {
                statusNoisedToPopulated(chunk);
                releasePopulationLock(chunk.getX(), chunk.getZ());
                statusPopulatedToFinished(chunk);
                // Remove recorded futures
                ((AllayUnsafeChunk) chunk.toUnsafeChunk()).setChunkSetCallback(() -> {
                    // The stored futures should always being removed
                    chunkNoiseFutures.remove(chunkHash);
                    chunkFutures.remove(chunkHash);
                });
                chunkFuture.complete(chunk);
            }, computeThreadPool).exceptionally(e -> {
                // TODO: unsafe here, locks should be released
                log.error("Error while processing population queue! Chunk {}, {}", chunk.getX(), chunk.getZ(), e);
                chunkFuture.complete(AllayUnsafeChunk.builder().voidChunk(chunk.getX(), chunk.getZ(), dimension.getDimensionInfo()).toSafeChunk());
                return null;
            });
        }
    }

    private boolean tryEnterPopulationStage(int x, int z) {
        // Requires noise to be generated for this chunk and adjacent chunks, and no locks
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                var chunkHash = HashUtils.hashXZ(x + i, z + j);
                if (populationLocks.contains(chunkHash)) {
                    return false;
                }
                var noiseFuture = chunkNoiseFutures.get(chunkHash);
                if (noiseFuture == null) {
                    // Chunk noise not generated
                    if (dimension.getChunkService().getChunk(chunkHash) == null) {
                        // Chunk not loaded
                        getOrCreateNoiseFuture(x + i, z + j);
                        return false;
                    }
                } else if (!noiseFuture.isDone()) {
                    // Chunk noise not finished generating
                    return false;
                }
            }
        }

        // Lock chunks
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                populationLocks.add(HashUtils.hashXZ(x + i, z + j));
            }
        }

        return true;
    }

    private void releasePopulationLock(int x, int z) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                populationLocks.remove(HashUtils.hashXZ(x + i, z + j));
            }
        }
    }

    @Override
    public CompletableFuture<Chunk> generateChunk(int x, int z) {
        CompletableFuture<Chunk> future = new CompletableFuture<>();
        var presentFuture = chunkFutures.putIfAbsent(HashUtils.hashXZ(x, z), future);
        if (presentFuture != null) {
            return presentFuture;
        } else {
            createChunkFuture(x, z).thenAccept(future::complete);
            return future;
        }
    }

    public CompletableFuture<Chunk> createChunkFuture(int x, int z) {
        var noiseFuture = getOrCreateNoiseFuture(x, z);
        CompletableFuture<Chunk> chunkFuture = new CompletableFuture<>();
        // Add chunk to population queue
        populationQueue.offer(new PopulationQueueEntry(chunkFuture, noiseFuture));
        return chunkFuture;
    }

    public CompletableFuture<Chunk> getOrCreateNoiseFuture(int x, int z) {
        // Compute chunk noise in parallel
        CompletableFuture<Chunk> noiseFuture = new CompletableFuture<>();
        var presentFuture = chunkNoiseFutures.putIfAbsent(HashUtils.hashXZ(x, z), noiseFuture);
        if (presentFuture == null) {
            generateNoisedChunk(x, z).thenAccept(noiseFuture::complete);
        } else noiseFuture = presentFuture;
        return noiseFuture;
    }

    /**
     * Generates noise for a chunk.
     * <p>
     * Noise generation is fully parallel as it does not require accessing adjacent chunks.
     */
    public CompletableFuture<Chunk> generateNoisedChunk(int x, int z) {
        var chunk = AllayUnsafeChunk.builder().newChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
        return CompletableFuture.supplyAsync(() -> statusNewToNoised(chunk), computeThreadPool);
    }

    private Chunk statusNewToNoised(Chunk chunk) {
        // Basic terrain
        var generateContext = new NoiseContext(chunk.toUnsafeChunk());
        for (var noiser : noisers) {
            if (!noiser.apply(generateContext)) {
                log.error("Failed to generate chunk {} with noiser {}", chunk, noiser.getName());
            }
        }

        ((AllayUnsafeChunk) chunk.toUnsafeChunk()).setState(ChunkState.NOISED);
        return chunk;
    }

    private void statusNoisedToPopulated(Chunk chunk) {
        // Decorate terrain
        var populateContext = new PopulateContext(chunk.toUnsafeChunk(), new PopulationStageChunkSource(chunk));
        for (var populator : populators) {
            try {
                if (!populator.apply(populateContext)) {
                    log.warn("Failed to populate chunk {} with populator {}", chunk, populator.getName());
                }
            } catch (Throwable t) {
                log.error("Error while populating chunk {} with populator {}", chunk, populator.getName(), t);
            }
        }

        ((AllayUnsafeChunk) chunk.toUnsafeChunk()).setState(ChunkState.POPULATED);
    }

    private void statusPopulatedToFinished(Chunk chunk) {
        // Spawn entities
        var entitySpawnContext = new EntitySpawnContext(chunk.toUnsafeChunk());
        for (var entitySpawner : entitySpawners) {
            try {
                if (!entitySpawner.apply(entitySpawnContext)) {
                    log.error("Failed to spawn entity in chunk {} with entity spawner {}", chunk, entitySpawner.getName());
                }
            } catch (Throwable t) {
                log.error("Error while spawning entity in chunk {} with entity spawner {}", chunk, entitySpawner.getName());
            }
        }

        ((AllayUnsafeChunk) chunk.toUnsafeChunk()).setState(ChunkState.ENTITY_SPAWNED);
        ((AllayUnsafeChunk) chunk.toUnsafeChunk()).setState(ChunkState.FINISHED);
    }

    protected static final class AllayWorldGeneratorBuilder implements WorldGenerator.WorldGeneratorBuilder {

        private String name;
        private WorldGeneratorType type = WorldGeneratorType.INFINITE;
        private String preset = "";
        private List<Noiser> noisers = List.of();
        private List<Populator> populators = List.of();
        private List<EntitySpawner> entitySpawners = List.of();
        private Consumer<Dimension> onDimensionSet = dimension -> {};

        public AllayWorldGeneratorBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AllayWorldGeneratorBuilder type(WorldGeneratorType type) {
            this.type = type;
            return this;
        }

        public AllayWorldGeneratorBuilder preset(String preset) {
            this.preset = preset;
            return this;
        }

        public AllayWorldGeneratorBuilder noisers(Noiser... noisers) {
            this.noisers = List.of(noisers);
            return this;
        }

        public AllayWorldGeneratorBuilder populators(Populator... populators) {
            this.populators = List.of(populators);
            return this;
        }

        public AllayWorldGeneratorBuilder entitySpawners(EntitySpawner... entitySpawners) {
            this.entitySpawners = List.of(entitySpawners);
            return this;
        }

        public AllayWorldGeneratorBuilder onDimensionSet(Consumer<Dimension> onDimensionSet) {
            this.onDimensionSet = onDimensionSet;
            return this;
        }

        public WorldGenerator build() {
            if (name == null || name.isBlank()) {
                throw new IllegalStateException("Name cannot be null or blank");
            }
            return new AllayWorldGenerator(name, type, preset, noisers, populators, entitySpawners, onDimensionSet);
        }
    }

    protected record PopulationQueueEntry(CompletableFuture<Chunk> chunkFuture, CompletableFuture<Chunk> noiseFuture) {}

    @AllArgsConstructor
    public final class PopulationStageChunkSource implements ChunkSource {
        private final Chunk currentChunk;

        @Override
        public Chunk getChunk(int x, int z) {
            if (x == currentChunk.getX() && z == currentChunk.getZ()) return currentChunk;
            if (!isInRange(x, z)) {
                log.debug("Attempted to access chunk out of range during chunk population stage! CurrentChunk: ({}. {}), RequestedChunk: ({}. {})", currentChunk.getX(), currentChunk.getZ(), x, z);
                return null;
            }

            var noiseFuture = chunkNoiseFutures.get(HashUtils.hashXZ(x, z));
            if (noiseFuture != null) {
                var chunk = noiseFuture.getNow(null);
                Preconditions.checkNotNull(chunk);
                return chunk;
            }

            // The return chunk can also be null, because
            // it may have been unloaded at this point
            return dimension.getChunkService().getChunk(x, z);
        }

        private boolean isInRange(int x, int z) {
            // Can only access adjacent chunks
            return Math.abs(x - currentChunk.getX()) <= 1 && Math.abs(z - currentChunk.getZ()) <= 1;
        }

        @Override
        public int minChunkZ() {
            return currentChunk.getZ() - 1;
        }

        @Override
        public int minChunkX() {
            return currentChunk.getX() - 1;
        }

        @Override
        public int maxChunkZ() {
            return currentChunk.getZ() + 1;
        }

        @Override
        public int maxChunkX() {
            return currentChunk.getX() + 1;
        }
    }
}
