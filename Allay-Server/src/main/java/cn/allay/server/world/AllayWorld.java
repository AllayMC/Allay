package cn.allay.server.world;

import cn.allay.api.math.Position3i;
import cn.allay.api.math.Position3ic;
import cn.allay.api.player.Client;
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
import org.joml.Vector3ic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public class AllayWorld implements World {
    private final WorldStorage worldStorage;
    @Getter
    private final WorldData worldData;
    @Getter
    private final WorldGenerator worldGenerator;
    @Getter
    private final Server server;
    @Getter
    private WorldType worldType;
    @Getter
    ForkJoinPool threadPool = new ForkJoinPool();
    @Getter
    Scheduler worldScheduler;
    @Getter
    ChunkService chunkService;
    @Getter
    EntityService entityService;
    @Getter
    private Thread worldMainThread;
    private final Map<Long, Client> clients = new ConcurrentHashMap<>();

    private AllayWorld(Server server,
                       WorldStorage worldStorage,
                       WorldData worldData,
                       WorldGenerator worldGenerator) {
        this.worldStorage = worldStorage;
        this.worldData = worldData;
        this.worldGenerator = worldGenerator;
        this.server = server;
        this.chunkService = new AllayChunkService(
                this,
                chunkService -> new AllayWorldGenerationService(threadPool, worldGenerator),
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

    @Override
    public GameType getWorldGameType() {
        return this.worldData.getGameType();
    }

    private void tick() {
        chunkService.tick();
        entityService.tick();
        worldScheduler.tick();
    }

    @Override
    public void setWorldGameType(GameType gameType) {
        this.worldData.setGameType(gameType);
    }

    @Override
    public String getName() {
        return this.worldData.getLevelName();
    }

    @Override
    public DimensionInfo getDimensionInfo() {
        return this.worldData.getDimensionInfo();
    }

    @Override
    public int getTickingRadius() {
        return this.worldData.getTickingRadius();
    }

    @Override
    public int getViewDistance() {
        return this.worldData.getViewDistance();
    }

    @Override
    public Position3ic getSpawnPosition() {
        Vector3ic spawnPoint = this.worldData.getSpawnPoint();
        return new Position3i(spawnPoint, this);
    }

    @Override
    public void setSpawnPosition(Position3ic newSpawn) {
        this.worldData.setSpawnPoint(newSpawn);
    }

    @Override
    public Difficulty getDifficulty() {
        return this.worldData.getDifficulty();
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
        private WorldData worldData;
        private WorldType worldType = WorldType.INFINITE;
        private WorldStorage worldStorage;
        private WorldGenerator worldGenerator;

        private WorldBuilder() {
        }

        public WorldBuilder worldData(WorldData worldData) {
            this.worldData = worldData;
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

        public WorldBuilder setWorldType(WorldType worldType) {
            this.worldType = worldType;
            return this;
        }

        public World build() {
            var world = new AllayWorld(Server.getInstance(), worldStorage, worldData, worldGenerator);
            world.worldType = worldType;
            return world;
        }
    }
}
