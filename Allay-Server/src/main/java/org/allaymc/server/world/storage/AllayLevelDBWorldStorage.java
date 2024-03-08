package org.allaymc.server.world.storage;

import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.datastruct.SemVersion;
import org.allaymc.api.exception.WorldStorageException;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Difficulty;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.gamerule.GameRules;
import org.allaymc.api.world.storage.NativeFileWorldStorage;
import org.allaymc.server.utils.LevelDBKeyUtils;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.iq80.leveldb.CompressionType;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteBatch;
import org.joml.Vector3i;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;

/**
 * Allay Project 8/22/2023
 *
 * @author Cool_Loong
 */
@Slf4j
public class AllayLevelDBWorldStorage implements NativeFileWorldStorage {
    private final Path path;
    private final DB db;
    private WorldData worldDataCache;

    public AllayLevelDBWorldStorage(Path path) throws WorldStorageException {
        this(path, new Options()
                .createIfMissing(true)
                .compressionType(CompressionType.ZLIB_RAW)
                .blockSize(64 * 1024)
        );
    }

    public AllayLevelDBWorldStorage(Path path, Options options) throws WorldStorageException {
        String worldName = path.getName(path.getNameCount() - 1).toString();
        File file = path.toFile();
        if (!file.exists()) file.mkdirs();
        this.path = path;
        worldDataCache = readWorldData();
        if (worldDataCache == null) initWorldData(worldName);

        File dbFolder = path.resolve("db").toFile();
        try {
            if (!dbFolder.exists()) dbFolder.mkdirs();
            db = net.daporkchop.ldbjni.LevelDB.PROVIDER.open(dbFolder, options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initWorldData(String worldName) {
        File levelDat = path.resolve("level.dat").toFile();
        try {
            // noinspection ResultOfMethodCallIgnored
            levelDat.createNewFile();
            worldDataCache = WorldData.builder().build();
            worldDataCache.setName(worldName);
            writeWorldData(worldDataCache);
            Files.copy(levelDat.toPath(), path.resolve("level.dat_old"), StandardCopyOption.REPLACE_EXISTING);
            Files.writeString(path.resolve("levelname.txt"), worldName, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompletableFuture<Chunk> readChunk(int x, int z, DimensionInfo dimensionInfo) throws WorldStorageException {
        return CompletableFuture.supplyAsync(() -> {
            AllayUnsafeChunk.Builder builder = AllayUnsafeChunk.builder()
                    .chunkX(x)
                    .chunkZ(z)
                    .dimensionInfo(dimensionInfo);
            byte[] versionValue = this.db.get(LevelDBKeyUtils.VERSION.getKey(x, z, dimensionInfo));
            if (versionValue == null) {
                versionValue = this.db.get(LevelDBKeyUtils.LEGACY_VERSION.getKey(x, z, dimensionInfo));
            }
            if (versionValue == null) {
                return builder.build().toSafeChunk();
            }
            byte[] finalized = this.db.get(LevelDBKeyUtils.CHUNK_FINALIZED_STATE.getKey(x, z, dimensionInfo));
            if (finalized == null) {
                builder.state(ChunkState.FINISHED);
            } else {
                builder.state(ChunkState.values()[Unpooled.wrappedBuffer(finalized).readIntLE() + 1]);
            }
            LevelDBChunkSerializer.INSTANCE.deserialize(this.db, builder);
            return builder.build().toSafeChunk();
        }, Server.getInstance().getVirtualThreadPool());
    }

    @Override
    public CompletableFuture<Void> writeChunk(Chunk chunk) throws WorldStorageException {
        return CompletableFuture.runAsync(() -> {
            try (WriteBatch writeBatch = this.db.createWriteBatch()) {
                chunk.batchProcess(c -> LevelDBChunkSerializer.INSTANCE.serialize(writeBatch, c));
                this.db.write(writeBatch);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, Server.getInstance().getVirtualThreadPool());
    }

    private static final byte[] levelDatMagic = new byte[]{10, 0, 0, 0, 68, 11, 0, 0};

    @Override
    public boolean containChunk(int x, int z, DimensionInfo dimensionInfo) {
        for (int ySection = dimensionInfo.minSectionY(); ySection <= dimensionInfo.maxSectionY(); ySection++) {
            byte[] bytes = db.get(LevelDBKeyUtils.CHUNK_SECTION_PREFIX.getKey(x, z, ySection, dimensionInfo));
            if (bytes != null) {
                return true;
            }
        }
        return false;
    }


    @Override
    public synchronized void writeWorldData(WorldData worldData) {
        File levelDat = path.resolve("level.dat").toFile();
        try (var output = new FileOutputStream(levelDat)) {
            if (levelDat.exists()) {
                Files.copy(path.resolve("level.dat"), path.resolve("level.dat_old"), StandardCopyOption.REPLACE_EXISTING);
            }
            output.write(levelDatMagic);//magic number
            var nbtOutputStream = NbtUtils.createWriterLE(output);
            nbtOutputStream.writeTag(createWorldDataNBT(worldData));
            worldDataCache = worldData;
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    @Override
    public synchronized WorldData readWorldData() throws WorldStorageException {
        File levelDat = path.resolve("level.dat").toFile();
        if (!levelDat.exists()) return null;
        try (var input = new FileInputStream(levelDat)) {
            //The first 8 bytes are magic number
            input.skip(8);
            NBTInputStream readerLE = NbtUtils.createReaderLE(new ByteArrayInputStream(input.readAllBytes()));
            NbtMap d = (NbtMap) readerLE.readTag();
            readerLE.close();
            NbtMap abilities = d.getCompound("abilities");
            NbtMap experiments = d.getCompound("experiments");
            GameRules gameRules = new GameRules();
            gameRules.put(GameRule.COMMAND_BLOCK_OUTPUT, d.getBoolean("commandblockoutput"));
            gameRules.put(GameRule.COMMAND_BLOCKS_ENABLED, d.getBoolean("commandblocksenabled"));
            gameRules.put(GameRule.DO_DAYLIGHT_CYCLE, d.getBoolean("dodaylightcycle"));
            gameRules.put(GameRule.DO_ENTITY_DROPS, d.getBoolean("doentitydrops"));
            gameRules.put(GameRule.DO_FIRE_TICK, d.getBoolean("dofiretick"));
            gameRules.put(GameRule.DO_IMMEDIATE_RESPAWN, d.getBoolean("doimmediaterespawn"));
            gameRules.put(GameRule.DO_INSOMNIA, d.getBoolean("doinsomnia"));
            gameRules.put(GameRule.DO_LIMITED_CRAFTING, d.getBoolean("dolimitedcrafting"));
            gameRules.put(GameRule.DO_MOB_LOOT, d.getBoolean("domobloot"));
            gameRules.put(GameRule.DO_MOB_SPAWNING, d.getBoolean("domobspawning"));
            gameRules.put(GameRule.DO_TILE_DROPS, d.getBoolean("dotiledrops"));
            gameRules.put(GameRule.DO_WEATHER_CYCLE, d.getBoolean("doweathercycle"));
            gameRules.put(GameRule.DROWNING_DAMAGE, d.getBoolean("drowningdamage"));
            gameRules.put(GameRule.FALL_DAMAGE, d.getBoolean("falldamage"));
            gameRules.put(GameRule.FIRE_DAMAGE, d.getBoolean("firedamage"));
            gameRules.put(GameRule.FREEZE_DAMAGE, d.getBoolean("freezedamage"));
            gameRules.put(GameRule.FUNCTION_COMMAND_LIMIT, d.getInt("functioncommandlimit"));
            gameRules.put(GameRule.KEEP_INVENTORY, d.getBoolean("keepinventory"));
            gameRules.put(GameRule.MAX_COMMAND_CHAIN_LENGTH, d.getInt("maxcommandchainlength"));
            gameRules.put(GameRule.MOB_GRIEFING, d.getBoolean("mobgriefing"));
            gameRules.put(GameRule.NATURAL_REGENERATION, d.getBoolean("naturalregeneration"));
            gameRules.put(GameRule.PVP, d.getBoolean("pvp"));
            gameRules.put(GameRule.RESPAWN_BLOCKS_EXPLODE, d.getBoolean("respawnblocksexplode"));
            gameRules.put(GameRule.SEND_COMMAND_FEEDBACK, d.getBoolean("sendcommandfeedback"));
            gameRules.put(GameRule.SHOW_BORDER_EFFECT, d.getBoolean("showbordereffect"));
            gameRules.put(GameRule.SHOW_COORDINATES, d.getBoolean("showcoordinates"));
            gameRules.put(GameRule.SHOW_DEATH_MESSAGES, d.getBoolean("showdeathmessages"));
            gameRules.put(GameRule.SHOW_TAGS, d.getBoolean("showtags"));
            gameRules.put(GameRule.SPAWN_RADIUS, d.getInt("spawnradius"));
            gameRules.put(GameRule.TNT_EXPLODES, d.getBoolean("tntexplodes"));
            return WorldData.builder()
                    .biomeOverride(d.getString("BiomeOverride"))
                    .centerMapsToOrigin(d.getBoolean("CenterMapsToOrigin"))
                    .confirmedPlatformLockedContent(d.getBoolean("ConfirmedPlatformLockedContent"))
                    .difficulty(Difficulty.from(d.getInt("Difficulty")))
                    .flatWorldLayers(d.getString("FlatWorldLayers"))
                    .forceGameType(d.getBoolean("ForceGameType"))
                    .gameType(GameType.from(d.getInt("GameType")))
                    .generator(d.getInt("Generator"))
                    .inventoryVersion(d.getString("InventoryVersion"))
                    .LANBroadcast(d.getBoolean("LANBroadcast"))
                    .LANBroadcastIntent(d.getBoolean("LANBroadcastIntent"))
                    .lastPlayed(d.getLong("LastPlayed"))
                    .name(d.getString("LevelName"))
                    .limitedWorldOriginPoint(new Vector3i(d.getInt("LimitedWorldOriginX"), d.getInt("LimitedWorldOriginY"), d.getInt("LimitedWorldOriginZ")))
                    .minimumCompatibleClientVersion(SemVersion.from(d.getIntArray("MinimumCompatibleClientVersion")))
                    .multiplayerGame(d.getBoolean("MultiplayerGame"))
                    .multiplayerGameIntent(d.getBoolean("MultiplayerGameIntent"))
                    .netherScale(d.getInt("NetherScale"))
                    .networkVersion(d.getInt("NetworkVersion"))
                    .platform(d.getInt("Platform"))
                    .platformBroadcastIntent(d.getInt("PlatformBroadcastIntent"))
                    .randomSeed(d.getLong("RandomSeed"))
                    .spawnV1Villagers(d.getBoolean("SpawnV1Villagers"))
                    .spawnPoint(new Vector3i(d.getInt("SpawnX"), d.getInt("SpawnY"), d.getInt("SpawnZ")))
                    .storageVersion(d.getInt("StorageVersion"))
                    .time(d.getLong("Time"))
                    .worldVersion(d.getInt("WorldVersion"))
                    .XBLBroadcastIntent(d.getInt("XBLBroadcastIntent"))
                    .abilities(WorldData.Abilities.builder()
                            .attackMobs(abilities.getBoolean("attackmobs"))
                            .attackPlayers(abilities.getBoolean("attackplayers"))
                            .build(abilities.getBoolean("build"))
                            .doorsAndSwitches(abilities.getBoolean("doorsandswitches"))
                            .flySpeed(abilities.getFloat("flySpeed"))
                            .flying(abilities.getBoolean("flying"))
                            .instaBuild(abilities.getBoolean("instabuild"))
                            .invulnerable(abilities.getBoolean("invulnerable"))
                            .lightning(abilities.getBoolean("lightning"))
                            .mayFly(abilities.getBoolean("mayfly"))
                            .mine(abilities.getBoolean("mine"))
                            .op(abilities.getBoolean("op"))
                            .openContainers(abilities.getBoolean("opencontainers"))
                            .teleport(abilities.getBoolean("teleport"))
                            .walkSpeed(abilities.getFloat("walkSpeed"))
                            .build())
                    .baseGameVersion(d.getString("baseGameVersion"))
                    .bonusChestEnabled(d.getBoolean("bonusChestEnabled"))
                    .bonusChestSpawned(d.getBoolean("bonusChestSpawned"))
                    .cheatsEnabled(d.getBoolean("cheatsEnabled"))
                    .commandsEnabled(d.getBoolean("commandsEnabled"))
                    .gameRules(gameRules)
                    .currentTick(d.getLong("currentTick"))
                    .daylightCycle(d.getInt("daylightCycle"))
                    .editorWorldType(d.getInt("editorWorldType"))
                    .eduOffer(d.getInt("eduOffer"))
                    .educationFeaturesEnabled(d.getBoolean("educationFeaturesEnabled"))
                    .experiments(WorldData.Experiments.builder()
                            .cameras(experiments.getBoolean("cameras"))
                            .dataDrivenBiomes(experiments.getBoolean("data_driven_biomes"))
                            .dataDrivenItems(experiments.getBoolean("data_driven_items"))
                            .experimentalMolangFeatures(experiments.getBoolean("experimental_molang_features"))
                            .experimentsEverUsed(experiments.getBoolean("experiments_ever_used"))
                            .savedWithToggledExperiments(experiments.getBoolean("saved_with_toggled_experiments"))
                            .upcomingCreatorFeatures(experiments.getBoolean("upcoming_creator_features"))
                            .villagerTradesRebalance(experiments.getBoolean("villager_trades_rebalance"))
                            .build())
                    .hasBeenLoadedInCreative(d.getBoolean("hasBeenLoadedInCreative"))
                    .hasLockedBehaviorPack(d.getBoolean("hasLockedBehaviorPack"))
                    .hasLockedResourcePack(d.getBoolean("hasLockedResourcePack"))
                    .immutableWorld(d.getBoolean("immutableWorld"))
                    .isCreatedInEditor(d.getBoolean("isCreatedInEditor"))
                    .isExportedFromEditor(d.getBoolean("isExportedFromEditor"))
                    .isFromLockedTemplate(d.getBoolean("isFromLockedTemplate"))
                    .isFromWorldTemplate(d.getBoolean("isFromWorldTemplate"))
                    .isRandomSeedAllowed(d.getBoolean("isRandomSeedAllowed"))
                    .isSingleUseWorld(d.getBoolean("isSingleUseWorld"))
                    .isWorldTemplateOptionLocked(d.getBoolean("isWorldTemplateOptionLocked"))
                    .lastOpenedWithVersion(SemVersion.from(d.getIntArray("lastOpenedWithVersion")))
                    .lightningLevel(d.getFloat("lightningLevel"))
                    .lightningTime(d.getInt("lightningTime"))
                    .limitedWorldDepth(d.getInt("limitedWorldDepth"))
                    .limitedWorldWidth(d.getInt("limitedWorldWidth"))
                    .permissionsLevel(d.getInt("permissionsLevel"))
                    .playerPermissionsLevel(d.getInt("playerPermissionsLevel"))
                    .playersSleepingPercentage(d.getInt("playerssleepingpercentage"))
                    .prid(d.getString("prid"))
                    .rainLevel(d.getFloat("rainLevel"))
                    .rainTime(d.getInt("rainTime"))
                    .randomTickSpeed(d.getInt("randomtickspeed"))
                    .recipesUnlock(d.getBoolean("recipesunlock"))
                    .requiresCopiedPackRemovalCheck(d.getBoolean("requiresCopiedPackRemovalCheck"))
                    .serverChunkTickRange(d.getInt("serverChunkTickRange"))
                    .spawnMobs(d.getBoolean("spawnMobs"))
                    .startWithMapEnabled(d.getBoolean("startWithMapEnabled"))
                    .texturePacksRequired(d.getBoolean("texturePacksRequired"))
                    .useMsaGamertagsOnly(d.getBoolean("useMsaGamertagsOnly"))
                    .worldStartCount(d.getLong("worldStartCount"))
                    .worldPolicies(WorldData.WorldPolicies.builder().build())
                    .build();
        } catch (FileNotFoundException e) {
            log.error("The level.dat file does not exist!");
        } catch (IOException e) {
            log.error("The level.dat file is broken!");
            return null;
        }
        throw new RuntimeException("level.dat is null!");
    }

    @Override
    public WorldData getWorldDataCache() {
        return worldDataCache;
    }

    public void close() {
        try {
            this.db.close();
            worldDataCache = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private NbtMap createWorldDataNBT(WorldData worldData) {
        NbtMapBuilder builder = NbtMap.builder();

        builder.putString("BiomeOverride", worldData.getBiomeOverride());
        builder.putBoolean("CenterMapsToOrigin", worldData.isCenterMapsToOrigin());
        builder.putBoolean("ConfirmedPlatformLockedContent", worldData.isConfirmedPlatformLockedContent());
        builder.putInt("Difficulty", worldData.getDifficulty().ordinal());
        builder.putString("FlatWorldLayers", worldData.getFlatWorldLayers());
        builder.putBoolean("ForceGameType", worldData.isForceGameType());
        builder.putInt("GameType", worldData.getGameType().ordinal());
        builder.putInt("Generator", worldData.getGenerator());
        builder.putString("InventoryVersion", worldData.getInventoryVersion());
        builder.putBoolean("LANBroadcast", worldData.isLANBroadcast());
        builder.putBoolean("LANBroadcastIntent", worldData.isLANBroadcastIntent());
        builder.putLong("LastPlayed", worldData.getLastPlayed());
        builder.putString("LevelName", worldData.getName());
        builder.putInt("LimitedWorldOriginX", worldData.getLimitedWorldOriginPoint().x());
        builder.putInt("LimitedWorldOriginY", worldData.getLimitedWorldOriginPoint().y());
        builder.putInt("LimitedWorldOriginZ", worldData.getLimitedWorldOriginPoint().z());
        builder.putIntArray("MinimumCompatibleClientVersion", worldData.getMinimumCompatibleClientVersion().toArray());
        builder.putBoolean("MultiplayerGame", worldData.isMultiplayerGame());
        builder.putBoolean("MultiplayerGameIntent", worldData.isMultiplayerGameIntent());
        builder.putInt("NetherScale", worldData.getNetherScale());
        builder.putInt("NetworkVersion", worldData.getNetworkVersion());
        builder.putInt("Platform", worldData.getPlatform());
        builder.putInt("PlatformBroadcastIntent", worldData.getPlatformBroadcastIntent());
        builder.putLong("RandomSeed", worldData.getRandomSeed());
        builder.putBoolean("SpawnV1Villagers", worldData.isSpawnV1Villagers());
        builder.putInt("SpawnX", worldData.getSpawnPoint().x());
        builder.putInt("SpawnY", worldData.getSpawnPoint().y());
        builder.putInt("SpawnZ", worldData.getSpawnPoint().z());
        builder.putInt("StorageVersion", worldData.getStorageVersion());
        builder.putLong("Time", worldData.getTime());
        builder.putInt("WorldVersion", worldData.getWorldVersion());
        builder.putInt("XBLBroadcastIntent", worldData.getXBLBroadcastIntent());
        NbtMap abilities = NbtMap.builder()
                .putBoolean("attackmobs", worldData.getAbilities().isAttackMobs())
                .putBoolean("attackplayers", worldData.getAbilities().isAttackPlayers())
                .putBoolean("build", worldData.getAbilities().isBuild())
                .putBoolean("doorsandswitches", worldData.getAbilities().isDoorsAndSwitches())
                .putBoolean("flying", worldData.getAbilities().isFlying())
                .putBoolean("instabuild", worldData.getAbilities().isInstaBuild())
                .putBoolean("invulnerable", worldData.getAbilities().isInvulnerable())
                .putBoolean("lightning", worldData.getAbilities().isLightning())
                .putBoolean("mayfly", worldData.getAbilities().isMayFly())
                .putBoolean("mine", worldData.getAbilities().isMine())
                .putBoolean("op", worldData.getAbilities().isOp())
                .putBoolean("opencontainers", worldData.getAbilities().isOpenContainers())
                .putBoolean("teleport", worldData.getAbilities().isTeleport())
                .putFloat("flySpeed", worldData.getAbilities().getFlySpeed())
                .putFloat("walkSpeed", worldData.getAbilities().getWalkSpeed())
                .build();
        NbtMap experiments = NbtMap.builder()
                .putBoolean("cameras", worldData.getExperiments().isCameras())
                .putBoolean("data_driven_biomes", worldData.getExperiments().isDataDrivenBiomes())
                .putBoolean("data_driven_items", worldData.getExperiments().isDataDrivenItems())
                .putBoolean("experimental_molang_features", worldData.getExperiments().isExperimentalMolangFeatures())
                .putBoolean("experiments_ever_used", worldData.getExperiments().isExperimentsEverUsed())
                .putBoolean("gametest", worldData.getExperiments().isGametest())
                .putBoolean("saved_with_toggled_experiments", worldData.getExperiments().isSavedWithToggledExperiments())
                .putBoolean("upcoming_creator_features", worldData.getExperiments().isUpcomingCreatorFeatures())
                .putBoolean("villager_trades_rebalance", worldData.getExperiments().isVillagerTradesRebalance())
                .build();
        builder.put("abilities", abilities);
        builder.put("experiments", experiments);

        builder.putBoolean("bonusChestEnabled", worldData.isBonusChestEnabled());
        builder.putBoolean("bonusChestSpawned", worldData.isBonusChestSpawned());
        builder.putBoolean("cheatsEnabled", worldData.isCheatsEnabled());
        builder.putBoolean("commandsEnabled", worldData.isCommandsEnabled());
        builder.putLong("currentTick", worldData.getCurrentTick());
        builder.putInt("daylightCycle", worldData.getDaylightCycle());
        builder.putInt("editorWorldType", worldData.getEditorWorldType());
        builder.putInt("eduOffer", worldData.getEduOffer());
        builder.putBoolean("educationFeaturesEnabled", worldData.isEducationFeaturesEnabled());

        builder.put("commandblockoutput", worldData.getGameRules().get(GameRule.COMMAND_BLOCK_OUTPUT));
        builder.put("commandblocksenabled", worldData.getGameRules().get(GameRule.COMMAND_BLOCKS_ENABLED));
        builder.put("dodaylightcycle", worldData.getGameRules().get(GameRule.DO_DAYLIGHT_CYCLE));
        builder.put("doentitydrops", worldData.getGameRules().get(GameRule.DO_ENTITY_DROPS));
        builder.put("dofiretick", worldData.getGameRules().get(GameRule.DO_FIRE_TICK));
        builder.put("doimmediaterespawn", worldData.getGameRules().get(GameRule.DO_IMMEDIATE_RESPAWN));
        builder.put("doinsomnia", worldData.getGameRules().get(GameRule.DO_INSOMNIA));
        builder.put("dolimitedcrafting", worldData.getGameRules().get(GameRule.DO_LIMITED_CRAFTING));
        builder.put("domobloot", worldData.getGameRules().get(GameRule.DO_MOB_LOOT));
        builder.put("domobspawning", worldData.getGameRules().get(GameRule.DO_MOB_SPAWNING));
        builder.put("dotiledrops", worldData.getGameRules().get(GameRule.DO_TILE_DROPS));
        builder.put("doweathercycle", worldData.getGameRules().get(GameRule.DO_WEATHER_CYCLE));
        builder.put("drowningdamage", worldData.getGameRules().get(GameRule.DROWNING_DAMAGE));
        builder.put("falldamage", worldData.getGameRules().get(GameRule.FALL_DAMAGE));
        builder.put("firedamage", worldData.getGameRules().get(GameRule.FIRE_DAMAGE));
        builder.put("freezedamage", worldData.getGameRules().get(GameRule.FREEZE_DAMAGE));
        builder.put("functioncommandlimit", worldData.getGameRules().get(GameRule.FUNCTION_COMMAND_LIMIT));
        builder.put("keepinventory", worldData.getGameRules().get(GameRule.KEEP_INVENTORY));
        builder.put("maxcommandchainlength", worldData.getGameRules().get(GameRule.MAX_COMMAND_CHAIN_LENGTH));
        builder.put("mobgriefing", worldData.getGameRules().get(GameRule.MOB_GRIEFING));
        builder.put("naturalregeneration", worldData.getGameRules().get(GameRule.NATURAL_REGENERATION));
        builder.put("pvp", worldData.getGameRules().get(GameRule.PVP));
        builder.put("respawnblocksexplode", worldData.getGameRules().get(GameRule.RESPAWN_BLOCKS_EXPLODE));
        builder.put("sendcommandfeedback", worldData.getGameRules().get(GameRule.SEND_COMMAND_FEEDBACK));
        builder.put("showbordereffect", worldData.getGameRules().get(GameRule.SHOW_BORDER_EFFECT));
        builder.put("showcoordinates", worldData.getGameRules().get(GameRule.SHOW_COORDINATES));
        builder.put("showdeathmessages", worldData.getGameRules().get(GameRule.SHOW_DEATH_MESSAGES));
        builder.put("showtags", worldData.getGameRules().get(GameRule.SHOW_TAGS));
        builder.put("spawnradius", worldData.getGameRules().get(GameRule.SPAWN_RADIUS));
        builder.put("tntexplodes", worldData.getGameRules().get(GameRule.TNT_EXPLODES));
        return builder.build();
    }

    @Override
    public Path getWorldFolderPath() {
        return path;
    }
}
