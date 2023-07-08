package cn.allay.server.world;

import cn.allay.api.math.location.FixedLoc;
import cn.allay.api.math.location.Loc;
import cn.allay.api.network.Client;
import cn.allay.api.scheduler.Scheduler;
import cn.allay.api.server.Server;
import cn.allay.api.world.*;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.chunk.ChunkService;
import cn.allay.api.world.entity.EntityService;
import cn.allay.api.world.generator.WorldGenerator;
import cn.allay.api.world.storage.WorldStorage;
import cn.allay.server.scheduler.AllayScheduler;
import cn.allay.server.utils.GameLoop;
import cn.allay.server.world.chunk.AllayChunkService;
import cn.allay.server.world.entity.AllayEntityService;
import cn.allay.server.world.generator.AllayWorldGenerationService;
import lombok.Getter;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;

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
    @Getter
    private int viewDistance;
    ForkJoinPool threadPool = new ForkJoinPool();
    @Getter
    Scheduler worldScheduler;
    @Getter
    ChunkService chunkService;
    @Getter
    EntityService entityService;
    @Getter
    private GameType worldGameType;
    @Getter
    private Thread worldMainThread;
    private Loc<Float> spawnLocation;
    private Difficulty difficulty;
    private final Map<Long, Client> clients = new ConcurrentHashMap<>();

    private AllayWorld(Server server,
                      WorldStorage worldStorage,
                      String name,
                      DimensionInfo dimensionInfo,
                      WorldGenerator worldGenerator) {
        this.worldStorage = worldStorage;
        loadWorldData();
        this.name = name;
        this.dimensionInfo = dimensionInfo;
        this.worldGenerator = worldGenerator;
        this.server = server;
        this.chunkService = new AllayChunkService(
                this,
                chunkService -> new AllayWorldGenerationService(
                        threadPool,
                        chunkService,
                        worldGenerator),
                worldStorage);
        this.entityService = new AllayEntityService(this);
        this.worldScheduler = new AllayScheduler(Executors.newVirtualThreadPerTaskExecutor());
    }

    @Override
    public void startTick() {
        if (worldMainThread != null) {
            throw new IllegalStateException("World is already ticking");
        }
        worldMainThread = Thread.ofPlatform()
                .name("Allay World Main Thread")
                .start(() -> {
                    GameLoop.builder()
                            .onTick(gameLoop -> tick())
                            .build()
                            .startLoop();
                });
    }

    private void tick() {
        chunkService.tick();
        entityService.tick();
        worldScheduler.tick();
    }

    private void loadWorldData() {
        var worldData = worldStorage.readWorldData();
        worldGameType = GameType.from(worldData.GameType());
        //TODO
    }

    @Override
    public void setWorldGameType(GameType gameType) {
        //TODO: Send to client
        this.worldGameType = gameType;
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
    public void addClient(Client client) {
        var playerEntity = client.getPlayerEntity();
        clients.put(playerEntity.getUniqueId(), client);
        addEntity(playerEntity);
        chunkService.addChunkLoader(client);
    }

    @Override
    public void removeClient(Client client) {
        var playerEntity = client.getPlayerEntity();
        clients.remove(playerEntity.getUniqueId());
        removeEntity(playerEntity);
        chunkService.removeChunkLoader(client);
    }

    @Override
    public @Nullable Chunk getChunk(int x, int z) {
        return chunkService.getChunk(x, z);
    }

    @Override
    public @Nullable Chunk getChunk(long chunkHash) {
        return null;
    }

    @Override
    public void setChunk(int x, int z, Chunk chunk) {
        chunkService.setChunk(x, z, chunk);
    }

    public static WorldBuilder builder() {
        return new WorldBuilder();
    }

    public static class WorldBuilder {
        private WorldType worldType = WorldType.INFINITE;
        private Server server = Server.getInstance();
        private String name = "world";
        private DimensionInfo dimensionInfo = DimensionInfo.OVERWORLD;
        private Loc<Float> spawnLocation = Loc.of(0f, 60f, 0f, null);
        private Difficulty difficulty = Difficulty.EASY;
        private WorldStorage worldStorage;
        private WorldGenerator worldGenerator;
        private int tickingRadius = server.getServerSettings().defaultTickingRadius();
        private int viewDistance = server.getServerSettings().defaultViewDistance();

        private WorldBuilder() {}

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

        public WorldBuilder setViewDistance(int viewDistance) {
            this.viewDistance = viewDistance;
            return this;
        }

        public World build() {
            var world = new AllayWorld(server, worldStorage, name, dimensionInfo, worldGenerator);
            spawnLocation.setWorld(world);
            world.spawnLocation = spawnLocation;
            world.difficulty = difficulty;
            world.worldType = worldType;
            world.tickingRadius = tickingRadius;
            world.viewDistance = viewDistance;
            return world;
        }
    }
}
