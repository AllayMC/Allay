package cn.allay.server.world;

import cn.allay.api.math.location.FixedLoc;
import cn.allay.api.math.location.Loc;
import cn.allay.api.server.Server;
import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.Difficulty;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.World;
import cn.allay.api.world.WorldType;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.generator.WorldGenerator;
import cn.allay.api.world.storage.WorldStorage;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class AllayWorld implements World {
    @Getter
    private final WorldStorage worldStorage;
    @Getter
    private final String name;
    @Getter
    private final DimensionInfo dimensionInfo;
    @Getter
    private final WorldGenerator worldGenerator;
    @Getter
    private final Server server;
    @Getter
    private WorldType worldType;
    @Getter
    private int tickingRadius;
    private final Map<Long, Chunk> chunks = new Long2ObjectOpenHashMap<>();
    private Loc<Float> spawnLocation;
    private Difficulty difficulty;

    public AllayWorld(Server server,
                      WorldStorage worldStorage,
                      String name,
                      DimensionInfo dimensionInfo,
                      WorldGenerator worldGenerator) {
        this.worldStorage = worldStorage;
        this.name = name;
        this.dimensionInfo = dimensionInfo;
        this.worldGenerator = worldGenerator;
        this.server = server;
    }

    @Override
    public void tick() {
        //TODO
    }

    @Override
    public FixedLoc<Float> getSpawnLocation() {
        return spawnLocation;
    }

    @Override
    public void setSpawnLocation(Loc<Float> newSpawn) {
        this.spawnLocation = newSpawn;
    }

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public @Nullable Chunk getChunk(int x, int z) {
        return chunks.get(HashUtils.hashXZ(x, z));
    }

    private static class WorldBuilder {
        private WorldType worldType = WorldType.INFINITE;
        private Server server = Server.getInstance();
        private String name = "world";
        private DimensionInfo dimensionInfo = DimensionInfo.OVERWORLD;
        private Loc<Float> spawnLocation = Loc.of(0f, 60f, 0f, null);
        private Difficulty difficulty = Difficulty.EASY;
        private WorldStorage worldStorage;
        private WorldGenerator worldGenerator;
        private int tickingRadius = server.getServerSettings().defaultTickingRadius();

        public WorldBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public WorldBuilder setDimensionInfo(DimensionInfo dimensionInfo) {
            this.dimensionInfo = dimensionInfo;
            return this;
        }

        public WorldBuilder setSpawnLocation(Loc<Float> spawnLocation) {
            this.spawnLocation = spawnLocation;
            return this;
        }

        public WorldBuilder setDifficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public WorldBuilder setWorldStorage(WorldStorage worldStorage) {
            this.worldStorage = worldStorage;
            return this;
        }

        public WorldBuilder setWorldGenerator(WorldGenerator worldGenerator) {
            this.worldGenerator = worldGenerator;
            this.worldType = worldGenerator.getGeneratorWorldType();
            return this;
        }

        public WorldBuilder setServer(Server server) {
            this.server = server;
            return this;
        }

        public WorldBuilder setWorldType(WorldType worldType) {
            this.worldType = worldType;
            return this;
        }

        public WorldBuilder setTickingRadius(int tickingRadius) {
            this.tickingRadius = tickingRadius;
            return this;
        }

        public World build() {
            var world = new AllayWorld(server, worldStorage, name, dimensionInfo, worldGenerator);
            world.spawnLocation = spawnLocation;
            world.difficulty = difficulty;
            world.worldType = worldType;
            world.tickingRadius = tickingRadius;
            return world;
        }
    }
}
