package org.allaymc.server.world.generator;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkAccessible;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.generator.WorldGeneratorType;
import org.allaymc.api.world.generator.context.EntitySpawnContext;
import org.allaymc.api.world.generator.context.NoiseContext;
import org.allaymc.api.world.generator.context.LightContext;
import org.allaymc.api.world.generator.context.PopulateContext;
import org.allaymc.api.world.generator.function.EntitySpawner;
import org.allaymc.api.world.generator.function.Noiser;
import org.allaymc.api.world.generator.function.Lighter;
import org.allaymc.api.world.generator.function.Populator;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
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
    private final List<Populator> populators ;
    private final List<Lighter> lighters;
    private final List<EntitySpawner> entitySpawners;
    private final Consumer<Dimension> onDimensionSet;
    // 原型区块是未完成生成的区块
    private final Map<Long, CompletableFuture<Chunk>> protoChunks = new Long2ObjectNonBlockingMap<>();
    // 存储已完成生成且正在被载入世界的基本区块
    private final Set<Long> beingSetProtoChunk = Collections.newSetFromMap(new Long2ObjectNonBlockingMap<>());

    @Getter
    private Dimension dimension; // Will be set later

    public static AllayWorldGeneratorBuilder builder() {
        return new AllayWorldGeneratorBuilder();
    }

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
    }

    private void init() {
        for (var noiser : noisers) noiser.init(this);
        for (var populator : populators) populator.init(this);
        for (var lighter : lighters) lighter.init(this);
        for (var entitySpawner : entitySpawners) entitySpawner.init(this);
    }

    @Override
    public void setDimension(Dimension dimension) {
        if (this.dimension != null) {
            throw new IllegalStateException("Dimension already set");
        }
        this.dimension = dimension;
        onDimensionSet.accept(dimension);
    }

    /**
     * 立即在此线程生成完整区块，完整区块的ChunkStatus为FINISHED，即可被载入世界
     */
    @Override
    public Chunk generateChunk(int x, int z) {
        var chunk = getOrGenerateProtoChunk(x, z);
        statusNoisedToFinished(chunk);
        markProtoChunkBeingSet(x, z);
        chunk.setChunkSetCallback(() -> {
            afterProtoChunkBeingSet(x, z);
            protoChunks.remove(HashUtils.hashXZ(x, z));
        });
        return chunk;
    }

    private Chunk getOrGenerateProtoChunk(int x, int z) {
        if (protoChunkBeingSet(x, z)) {
            log.error("Trying to access a proto chunk which is being set: x: " + x + ", z: " + z);
            return AllayUnsafeChunk.builder().emptyChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
        }
        CompletableFuture<Chunk> future = new CompletableFuture<>();
        // 这里的putIfAbsent()保证了只有一个线程可以写入future，其他线程只能获取这一个线程写入的future
        var presentFuture = protoChunks.putIfAbsent(HashUtils.hashXZ(x, z), future);
        if (presentFuture != null) {
            // 原型区块已经在生成或已生成完毕
            // 等待生成完毕后返回区块，或直接返回若已生成完毕
            return presentFuture.join();
        }
        var chunk = generateProtoChunk(x, z);
        future.complete(chunk);
        return chunk;
    }

    private Chunk generateProtoChunk(int x, int z) {
        var chunk = AllayUnsafeChunk.builder().emptyChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
        statusEmptyToNoised(chunk);
        return chunk;
    }

    private void markProtoChunkBeingSet(int x, int z) {
        beingSetProtoChunk.add(HashUtils.hashXZ(x, z));
    }

    private void afterProtoChunkBeingSet(int x, int z) {
        beingSetProtoChunk.remove(HashUtils.hashXZ(x, z));
    }

    private boolean protoChunkBeingSet(int x, int z) {
        return beingSetProtoChunk.contains(HashUtils.hashXZ(x, z));
    }

    private void statusEmptyToFinished(Chunk chunk) {
        statusEmptyToNoised(chunk);
        statusNoisedToFinished(chunk);
    }

    private void statusEmptyToNoised(Chunk chunk) {
        // 基本地形
        var generateContext = new NoiseContext(chunk.toUnsafeChunk());
        for (var noiser : noisers) {
            if (!noiser.apply(generateContext)) {
                log.error("Failed to generate chunk {} with noiser {}", chunk, noiser.getName());
            }
        }
        chunk.setState(ChunkState.NOISED);
    }

    private void statusNoisedToFinished(Chunk chunk) {
         // 装饰地形
        var chunkAccessor = new WorldGeneratorChunkAccessor(chunk);
        var populateContext = new PopulateContext(chunk.toUnsafeChunk(), chunkAccessor);
         for (var populator : populators) {
             if (!populator.apply(populateContext)) {
                 log.error("Failed to populate chunk {} with populator {}", chunk, populator.getName());
             }
         }
         chunk.setState(ChunkState.POPULATED);
         // 烘培光照
         var lightContext = new LightContext(chunk.toUnsafeChunk());
         for (var lighter : lighters) {
             if (!lighter.apply(lightContext)) {
                 log.error("Failed to light chunk {} with lighter {}", chunk, lighter.getName());
             }
         }
         chunk.setState(ChunkState.LIGHTED);
         // 生成实体
        var entitySpawnContext = new EntitySpawnContext(chunk.toUnsafeChunk(), chunkAccessor);
        for (var entitySpawner : entitySpawners) {
            if (!entitySpawner.apply(entitySpawnContext)) {
                log.error("Failed to spawn entity in chunk {} with entity spawner {}", chunk, entitySpawner.getName());
            }
        }
        chunk.setState(ChunkState.ENTITY_SPAWNED);
        chunk.setState(ChunkState.FINISHED);
    }

    public final class WorldGeneratorChunkAccessor implements ChunkAccessible {
        private final Chunk currentChunk;

        public WorldGeneratorChunkAccessor(Chunk currentChunk) {
            this.currentChunk = currentChunk;
        }

        @Override
        public Chunk getChunk(int x, int z) {
            if (x == currentChunk.getX() && z == currentChunk.getZ()) {
                return currentChunk;
            }
            var chunkService = dimension.getChunkService();

            // 先获取future，避免在此过程中区块完成加载导致future被删除
            CompletableFuture<Chunk> future = chunkService.getChunkLoadingFuture(x, z);
            if (protoChunkBeingSet(x, z)) {
                // 即使在此过程中future被删除（区块完成加载），此代码依然可以工作因为
                // 区块完成加载后，future.join()等效于chunkService.getChunk(x, z)
                return future.join();
            }

            var chunk = chunkService.getChunk(x, z);
            // 若区块已生成则直接返回
            if (chunk != null) return chunk;

            // 获取或生成原型区块供使用
            // 此时有小概率出现目标区块已进入"BeingSet"状态的情况
            // 但是这不会产生问题，因为在这种情况下获取的原型区块等效于稍后的chunkService.getChunk(x, z)
            chunk = getOrGenerateProtoChunk(x, z);
            return chunk;
        }
    }

    public static final class AllayWorldGeneratorBuilder implements WorldGenerator.WorldGeneratorBuilder {

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
}
