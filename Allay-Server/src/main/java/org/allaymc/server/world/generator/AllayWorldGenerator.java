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

import java.util.List;
import java.util.Map;
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
    // 存储基本区块，基本区块的ChunkStatus为NOISED
    // 基本区块只会在当一个区块生成时需要访问相邻未生成区块时产生
    private final Map<Long, Chunk> basicChunks = new Long2ObjectNonBlockingMap<>();

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
        Chunk chunk = basicChunks.remove(HashUtils.hashXZ(x, z));
        if (chunk != null) {
            statusNoisedToFinished(chunk);
        } else {
            chunk = AllayUnsafeChunk.builder().emptyChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
            statusEmptyToFinished(chunk);
        }
        return chunk;
    }

    private Chunk getOrGenerateBasicChunk(int x, int z) {
        return basicChunks.computeIfAbsent(HashUtils.hashXZ(x, z), unused -> generateBasicChunk(x, z));
    }

    /**
     * 生成基本区块，基本区块的ChunkStatus为NOISED
     */
    private Chunk generateBasicChunk(int x, int z) {
        var chunk = AllayUnsafeChunk.builder().emptyChunk(x, z, dimension.getDimensionInfo()).toSafeChunk();
        statusEmptyToNoised(chunk);
        return chunk;
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
            // 若区块已生成则直接返回
            var chunk = dimension.getChunkService().getChunk(x, z);
            if (chunk != null) return chunk;
            // 生成基本区块供使用
            chunk = getOrGenerateBasicChunk(x, z);
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
