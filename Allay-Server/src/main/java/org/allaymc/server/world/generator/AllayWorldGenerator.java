package org.allaymc.server.world.generator;

import com.google.common.base.Preconditions;
import io.netty.util.internal.PlatformDependent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.GameLoop;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkAccessible;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.generator.WorldGeneratorType;
import org.allaymc.api.world.generator.context.EntitySpawnContext;
import org.allaymc.api.world.generator.context.LightContext;
import org.allaymc.api.world.generator.context.NoiseContext;
import org.allaymc.api.world.generator.context.PopulateContext;
import org.allaymc.api.world.generator.function.EntitySpawner;
import org.allaymc.api.world.generator.function.Lighter;
import org.allaymc.api.world.generator.function.Noiser;
import org.allaymc.api.world.generator.function.Populator;
import org.allaymc.server.AllayServer;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
@Slf4j
public final class AllayWorldGenerator implements WorldGenerator {

    @Getter
    private final String name;
    @Getter
    private final WorldGeneratorType type;
    @Getter
    private final String preset;
    private final List<Noiser> noisers;
    private final List<Populator> populators;
    private final List<Lighter> lighters;
    private final List<EntitySpawner> entitySpawners;
    private final Consumer<Dimension> onDimensionSet;
    private final Map<Long, CompletableFuture<Chunk>> chunkNoiseFutures = new Long2ObjectNonBlockingMap<>();
    private final Map<Long, CompletableFuture<Chunk>> chunkFutures = new Long2ObjectNonBlockingMap<>();
    private final Queue<PopulationQueueEntry> populationQueue = PlatformDependent.newMpscQueue();
    private final Set<Long> populationLocks = Collections.newSetFromMap(new Long2ObjectNonBlockingMap<>());
    private final ExecutorService computeThreadPool = AllayServer.getInstance().getComputeThreadPool();

    @Getter
    private Dimension dimension; // Will be set later

    private AllayWorldGenerator(
            String name,
            WorldGeneratorType type,
            String preset,
            List<Noiser> noisers,
            List<Populator> populators,
            List<Lighter> lighters,
            List<EntitySpawner> entitySpawners,
            Consumer<Dimension> onDimensionSet
    ) {
        this.name = name;
        this.type = type;
        this.preset = preset;
        this.noisers = noisers;
        this.populators = populators;
        this.lighters = lighters;
        this.entitySpawners = entitySpawners;
        this.onDimensionSet = onDimensionSet;
        init();
        GameLoop populationQueueLoop = GameLoop.builder()
                .onTick(this::processPopulationQueue)
                .build();
        Thread.ofVirtual().start(populationQueueLoop::startLoop);
    }

    public static WorldGeneratorBuilder builder() {
        return new AllayWorldGeneratorBuilder();
    }

    private void init() {
        noisers.forEach(noiser -> noiser.init(this));
        populators.forEach(populator -> populator.init(this));
        lighters.forEach(lighter -> lighter.init(this));
        entitySpawners.forEach(entitySpawner -> entitySpawner.init(this));
    }

    @Override
    public void setDimension(Dimension dimension) {
        if (this.dimension != null) throw new IllegalStateException("Dimension already set");
        this.dimension = dimension;
        onDimensionSet.accept(dimension);
    }

    private void processPopulationQueue(GameLoop loop) {
        if (!Server.getInstance().isRunning()) {
            loop.stop();
            return;
        }

        PopulationQueueEntry entry;
        while ((entry = populationQueue.poll()) != null) {
            var noiseFuture = entry.noiseFuture();
            var chunk = noiseFuture.getNow(null);
            if (chunk == null) {
                // Noise not generated, re-add to queue
                populationQueue.add(entry);
                continue;
            }

            var canPopulate = tryEnterPopulationStage(chunk.getX(), chunk.getZ());
            if (!canPopulate) {
                // This chunk or adjacent chunk is locked, re-add to queue
                populationQueue.add(entry);
                continue;
            }

            var chunkFuture = entry.chunkFuture();
            noiseFuture.thenAcceptAsync($ -> {
                statusNoisedToPopulated(chunk);
                releasePopulationLock(chunk.getX(), chunk.getZ());
                statusPopulatedToFinished(chunk);
                var chunkHash = HashUtils.hashXZ(chunk.getX(), chunk.getZ());
                // Remove recorded futures
                chunk.setChunkSetCallback(() -> {
                    chunkNoiseFutures.remove(chunkHash);
                    chunkFutures.remove(chunkHash);
                });
                chunkFuture.complete(chunk);
            }, computeThreadPool);
        }
    }

    private boolean tryEnterPopulationStage(int x, int z) {
        // Requires noise to be generated for this chunk and adjacent chunks, and no locks
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                var chunkHash = HashUtils.hashXZ(x + i, z + j);
                if (populationLocks.contains(chunkHash)) return false;
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
        populationQueue.add(new PopulationQueueEntry(chunkFuture, noiseFuture));
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
        var chunk = AllayUnsafeChunk.builder().emptyChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
        return CompletableFuture.supplyAsync(() -> statusEmptyToNoised(chunk), computeThreadPool);
    }

    private Chunk statusEmptyToNoised(Chunk chunk) {
        // Basic terrain
        var generateContext = new NoiseContext(chunk.toUnsafeChunk());
        for (var noiser : noisers) {
            if (!noiser.apply(generateContext)) {
                log.error("Failed to generate chunk {} with noiser {}", chunk, noiser.getName());
            }
        }

        chunk.setState(ChunkState.NOISED);
        return chunk;
    }

    private void statusNoisedToPopulated(Chunk chunk) {
        // Decorate terrain
        var populateContext = new PopulateContext(chunk.toUnsafeChunk(), new PopulationStageChunkAccessor(chunk));
        for (var populator : populators) {
            if (!populator.apply(populateContext)) {
                log.error("Failed to populate chunk {} with populator {}", chunk, populator.getName());
            }
        }

        chunk.setState(ChunkState.POPULATED);
    }

    private void statusPopulatedToFinished(Chunk chunk) {
        // Bake lighting
        var lightContext = new LightContext(chunk.toUnsafeChunk());
        for (var lighter : lighters) {
            if (!lighter.apply(lightContext)) {
                log.error("Failed to light chunk {} with lighter {}", chunk, lighter.getName());
            }
        }

        chunk.setState(ChunkState.LIGHTED);

        // Generate entities
        var entitySpawnContext = new EntitySpawnContext(chunk.toUnsafeChunk());
        for (var entitySpawner : entitySpawners) {
            if (!entitySpawner.apply(entitySpawnContext)) {
                log.error("Failed to spawn entity in chunk {} with entity spawner {}", chunk, entitySpawner.getName());
            }
        }

        chunk.setState(ChunkState.ENTITY_SPAWNED);
        chunk.setState(ChunkState.FINISHED);
    }

    protected static final class AllayWorldGeneratorBuilder implements WorldGenerator.WorldGeneratorBuilder {

        private String name;
        private WorldGeneratorType type = WorldGeneratorType.INFINITE;
        private String preset = "";
        private List<Noiser> noisers = List.of();
        private List<Populator> populators = List.of();
        private List<Lighter> lighters = List.of();
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

        public AllayWorldGeneratorBuilder lighters(Lighter... lighters) {
            this.lighters = List.of(lighters);
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
            return new AllayWorldGenerator(name, type, preset, noisers, populators, lighters, entitySpawners, onDimensionSet);
        }
    }

    protected record PopulationQueueEntry(CompletableFuture<Chunk> chunkFuture, CompletableFuture<Chunk> noiseFuture) {}

    @AllArgsConstructor
    public final class PopulationStageChunkAccessor implements ChunkAccessible {
        private final Chunk currentChunk;

        @Override
        public Chunk getChunk(int x, int z) {
            if (x == currentChunk.getX() && z == currentChunk.getZ()) return currentChunk;
            if (!isInRange(x, z)) {
//                log.warn("Attempted to access chunk out of range during chunk population stage! CurrentChunk: ({}. {}), RequestedChunk: ({}. {})", currentChunk.getX(), currentChunk.getZ(), x, z);
                return null;
            }

            var loadedChunk = dimension.getChunkService().getChunk(x, z);
            if (loadedChunk != null) return loadedChunk;

            var noiseFuture = chunkNoiseFutures.get(HashUtils.hashXZ(x, z));
            Preconditions.checkNotNull(noiseFuture);

            var chunk = noiseFuture.getNow(null);
            Preconditions.checkNotNull(chunk);
            return chunk;
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
