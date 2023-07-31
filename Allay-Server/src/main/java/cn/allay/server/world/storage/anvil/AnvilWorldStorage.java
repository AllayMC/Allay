package cn.allay.server.world.storage.anvil;

import cn.allay.api.world.Difficulty;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.gamerule.GameRule;
import cn.allay.api.world.gamerule.GameRules;
import cn.allay.api.world.storage.NativeFileWorldStorage;
import cn.allay.api.world.storage.WorldStorageException;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

/**
 * Allay Project 2023/6/4
 *
 * @author Cool_Loong
 */
public class AnvilWorldStorage implements NativeFileWorldStorage {
    private final Path worldFolderPath;
    private final Path regionFolderPath;
    private final File levelDat;
    private final Long2ObjectMap<AnvilRegionFile> regions = new Long2ObjectOpenHashMap<>();

    public AnvilWorldStorage(Path worldFolderPath) {
        this.worldFolderPath = worldFolderPath;
        this.regionFolderPath = worldFolderPath.resolve("region");
        this.levelDat = worldFolderPath.resolve("level.dat").toFile();
        if (!this.levelDat.exists()) {
            try {
                this.levelDat.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Path getWorldFolderPath() {
        return worldFolderPath;
    }

    @Override
    public CompletableFuture<Chunk> readChunk(int x, int z, DimensionInfo dimensionData) {
        //TODO: read chunk
//        long chunkHash = chunkHash(x, z);
//        AnvilRegionFile region = regions.get(chunkHash);
//        try {
//            if (region == null) {
//                region = new AnvilRegionFile(regionFolderPath, x, z);
//                regions.put(chunkHash, region);
//            }
//            AnvilRegionFile finalRegion = region;
//            CompletableFuture.runAsync(()->{
//                NbtMap chunkNBT;
//                try {
//                    chunkNBT = finalRegion.readChunkData(x, z);
//                    Chunk chunk = new AnvilChunk(chunkNBT, dimensionData);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            })
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    @Override
    public CompletableFuture<Void> writeChunk(int x, int z, Chunk chunk) {
        //TODO: write chunk
        return null;
    }

    @Override
    public boolean containChunk(int x, int z) {
        //TODO
        return false;
    }

    @Override
    public synchronized void writeWorldData(WorldData worldData) {
        File levelDat = worldFolderPath.resolve("level.dat").toFile();
        try (var input = NbtUtils.createGZIPWriter(new FileOutputStream(levelDat))) {
            input.writeTag(createWorldDataNBT(worldData));
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    @Override
    @NotNull
    public synchronized WorldData readWorldData() throws WorldStorageException {
        File levelDat = worldFolderPath.resolve("level.dat").toFile();
        try (var input = NbtUtils.createGZIPReader(new FileInputStream(levelDat))) {
            return createWorldData(((NbtMap) input.readTag()));
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    @Override
    public void close() {

    }

    private WorldData createWorldData(NbtMap data) {
        boolean allay = data.containsKey("allay");
        data = allay ? data : data.getCompound("Data");
        WorldData.WorldDataBuilder builder = WorldData.builder();
        NbtMap gameRulesNbt = data.getCompound("GameRules");
        GameRules gameRules = new GameRules();
        for (var key : gameRulesNbt.keySet()) {
            GameRule gameRule = GameRule.fromName(key);
            if (gameRule != null) {
                if (gameRule.getType() == GameRule.Type.INT) {
                    gameRules.put(gameRule, Integer.parseInt(gameRulesNbt.get(key).toString()));
                } else {
                    gameRules.put(gameRule, Boolean.parseBoolean(gameRulesNbt.get(key).toString()));
                }
            }
        }
        builder.gameRules(gameRules)
                .spawnPoint(Vec3i.of(data.getInt("SpawnX"), data.getInt("SpawnY"), data.getInt("SpawnZ")))
                .difficulty(Difficulty.getDifficulty(data.getInt("Difficulty")))
                .storageVersion(data.getInt("version"))
                .time(data.getLong("Time"))
                .lightningTick(data.getInt("thunderTime"))
                .rainTick(data.getInt("rainTime"))
                .gameType(GameType.from(data.getInt("GameType")))
                .levelName(data.getString("LevelName"))
                .allay(true);
        if (allay) {
            NbtMap dimensionInfo = data.getCompound("DimensionInfo");
            return builder
                    .dimensionInfo(new DimensionInfo(dimensionInfo.getInt("dimensionId"),
                            dimensionInfo.getInt("minHeight"),
                            dimensionInfo.getInt("maxHeight"),
                            dimensionInfo.getInt("chunkSectionSize")
                    ))
                    .tickingRadius(data.getInt("tickingRadius"))
                    .viewDistance(data.getInt("viewDistance"))
                    .generatorOptions(data.getString("generatorOptions"))
                    .randomSeed(data.getLong("seed"))
                    .build();
        } else {
            return builder
                    .dimensionInfo(DimensionInfo.fromName(IdentifierUtils.tryParse(data.getCompound("Player").getString("Dimension", "minecraft:overworld"))))
                    .tickingRadius(Server.getInstance().getServerSettings().defaultTickingRadius())
                    .viewDistance(Server.getInstance().getServerSettings().defaultViewDistance())
                    .generatorOptions("")
                    .randomSeed(data.getCompound("WorldGenSettings").getLong("seed"))
                    .build();
        }
    }

    private NbtMap createWorldDataNBT(WorldData data) {
        NbtMapBuilder builder = NbtMap.builder();
        GameRules gameRules = data.getGameRules();

        NbtMapBuilder builder2 = NbtMap.builder();
        for (var entry : gameRules.getGameRules().entrySet()) {
            builder2.putString(entry.getKey().getName(), entry.getValue().toString());
        }

        NbtMapBuilder builder3 = NbtMap.builder();
        builder3.putInt("dimensionId", data.getDimensionInfo().dimensionId())
                .putInt("minHeight", data.getDimensionInfo().minHeight())
                .putInt("maxHeight", data.getDimensionInfo().maxHeight())
                .putInt("chunkSectionSize", data.getDimensionInfo().chunkSectionSize());

        return builder.putCompound("GameRules", builder2.build())
                .putInt("SpawnX", data.getSpawnPoint().x())
                .putInt("SpawnY", data.getSpawnPoint().y())
                .putInt("SpawnZ", data.getSpawnPoint().z())
                .putInt("Difficulty", data.getDifficulty().ordinal())
                .putInt("version", data.getStorageVersion())
                .putLong("Time", data.getTime())
                .putLong("thunderTime", data.getLightningTick())
                .putLong("rainTime", data.getRainTick())
                .putInt("GameType", data.getGameType().ordinal())
                .putInt("tickingRadius", data.getTickingRadius())
                .putInt("viewDistance", data.getViewDistance())
                .putString("LevelName", data.getLevelName())
                .putString("generatorOptions", data.getGeneratorOptions())
                .putLong("seed", data.getRandomSeed())
                .putCompound("DimensionInfo", builder3.build())
                .putBoolean("allay", data.isAllay())
                .build();
    }
}
