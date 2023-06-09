package cn.allay.server.world.anvil;

import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.WorldData;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.storage.NativeFileWorldProvider;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: Cool_Loong <br>
 * Date: 6/4/2023 <br>
 * Allay Project
 */
public class AnvilWorldProvider implements NativeFileWorldProvider {
    private final Path worldFolderPath;
    private final Path regionFolderPath;
    private final Long2ObjectMap<AnvilRegionFile> regions = new Long2ObjectOpenHashMap<>();
    private final ConcurrentHashMap<Long, AnvilRegionFile> chunkCaches = new ConcurrentHashMap<>();

    public AnvilWorldProvider(Path worldFolderPath) {
        this.worldFolderPath = worldFolderPath;
        this.regionFolderPath = worldFolderPath.resolve("region");
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
    public CompletableFuture<Void> writeChunk(Chunk chunk) {
        //TODO: write chunk
        return null;
    }

    @Override
    public void writeWorldData(WorldData worldData) {
        //TODO
    }

    @Override
    @NotNull
    public WorldData readWorldData() throws IOException {
        File levelDat = worldFolderPath.resolve("level.dat").toFile();
        try (var input = NbtUtils.createGZIPReader(new FileInputStream(levelDat))) {
            return createWorldData(((NbtMap) input.readTag()).getCompound("Data"));
        }
    }

    private WorldData createWorldData(NbtMap data) {
        return WorldData.builder()
                .WorldVersion(1)
                .lightningTime(data.getLong("lightningTime"))
                .Platform(2)
                .Difficulty(data.getInt("Difficulty"))
                .hasLockedResourcePack(false)
                .GameType(data.getInt("GameType"))
                .isFromWorldTemplate(false)
                .hasBeenLoadedInCreative(true)
                .lightningLevel(1f)
                .XBLBroadcastIntent(2)
                .startWithMapEnabled(false)
                .rainLevel(0f)
                .PlatformBroadcastIntent(2)
                .lastOpenedWithVersion(data.getList("lastOpenedWithVersion", NbtType.INT))
                .serverChunkTickRange(data.getInt("serverChunkTickRange"))
                .educationFeaturesEnabled(false)
                .isExportedFromEditor(false)
                .isCreatedInEditor(false)
                .Generator(data.getInt("Generator"))
                .RandomSeed(data.getLong("RandomSeed"))
                .NetworkVersion(data.getInt("NetworkVersion"))
                .experiments(Map.of())
                .spawnMobs(data.getBoolean("spawnMobs"))
                .InventoryVersion(data.getString("InventoryVersion"))
                .CenterMapsToOrigin(false)
                .LANBroadcastIntent(true)
                .currentTick(data.getLong("currentTick"))
                .isFromLockedTemplate(false)
                .rainTime(data.getInt("rainTime"))
                .LANBroadcast(true)
                .worldPolicies(Map.of())
                .hasLockedBehaviorPack(false)
                .cheatsEnabled(data.getBoolean("cheatsEnabled"))
                .StorageVersion(data.getInt("StorageVersion"))
                .MinimumCompatibleClientVersion(data.getList("MinimumCompatibleClientVersion", NbtType.INT))
                .ConfirmedPlatformLockedContent(false)
                .texturePacksRequired(data.getBoolean("texturePacksRequired"))
                .bonusChestEnabled(data.getBoolean("bonusChestEnabled"))
                .abilities(Map.of())
                .daylightCycle(0)
                .baseGameVersion("*")
                .bonusChestSpawned(false)
                .MultiplayerGame(true)
                .isSingleUseWorld(false)
                .permissionsLevel(0)
                .spawnX(data.getInt("spawnX"))
                .spawnY(data.getInt("spawnY"))
                .spawnZ(data.getInt("spawnZ"))
                .prid("")
                .eduOffer(0)
                .worldStartCount(data.getLong("worldStartCount"))
                .forceGameType(data.getBoolean("forceGameType"))
                .MultiplayerGameIntent(false)
                .LevelName(data.getString("LevelName"))
                .isWorldTemplateOptionLocked(false)
                .Time(data.getLong("Time"))
                .limitedWorldDepth(16)
                .limitedWorldWidth(16)
                .LimitedWorldOriginY(32767)
                .requiresCopiedPackRemovalCheck(false)
                .LimitedWorldOriginX(-83)
                .LimitedWorldOriginZ(-86)
                .BiomeOverride("")
                .SpawnV1Villagers(data.getBoolean("SpawnV1Villagers"))
                .LastPlayed(data.getLong("LastPlayed"))
                .NetherScale(0)
                .useMsaGamertagsOnly(false)
                .FlatWorldLayers("""
                        {"biome_id":1,"block_layers":[{"block_name":"minecraft:bedrock","count":1},{"block_name":"minecraft:dirt","count":2},{"block_name":"minecraft:grass","count":1}],"encoding_version":6,"structure_options":null,"world_version":"version.post_1_18"}
                        """)
                .immutableWorld(false)
                .playerPermissionsLevel(1)
                .build();

    }

    @Override
    public void close() {

    }

    private static long chunkHash(int x, int z) {
        return (((long) x) << 32) | (z & 0xffffffffL);
    }
}
