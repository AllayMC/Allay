package org.allaymc.api.world;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.world.DifficultyChangeEvent;
import org.allaymc.api.eventbus.event.world.GameRuleChangeEvent;
import org.allaymc.api.eventbus.event.world.SpawnPointChangeEvent;
import org.allaymc.api.eventbus.event.world.TimeChangeEvent;
import org.allaymc.api.network.ProtocolInfo;
import org.allaymc.api.utils.SemVersion;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.gamerule.GameRules;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.SetTimePacket;
import org.intellij.lang.annotations.Language;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Collection;

@Getter
@Builder
public class WorldData {
    public static final long TIME_DAY = 0;
    public static final long TIME_NOON = 6000;
    public static final long TIME_SUNSET = 12000;
    public static final long TIME_NIGHT = 14000;
    public static final long TIME_MIDNIGHT = 18000;
    public static final long TIME_SUNRISE = 23000;
    public static final long TIME_FULL = 24000;

    // NOTICE: The following values won't be written into the file
    World world;

    // NOTICE: The following values are used
    @Builder.Default
    Difficulty difficulty = Difficulty.EASY;
    @Builder.Default
    boolean forceGameType = false;
    @Builder.Default
    org.cloudburstmc.protocol.bedrock.data.GameType gameType = GameType.CREATIVE;
    @Builder.Default
    String name = "Bedrock level";
    @Builder.Default
    org.joml.Vector3ic spawnPoint = new Vector3i(0, 64, 0);
    @Builder.Default
    long time = 0L;
    @Builder.Default
    GameRules gameRules = new GameRules();

    // NOTICE: The following properties are unused, however we keep them for vanilla compatibility reason
    @Builder.Default
    org.joml.Vector3ic limitedWorldOriginPoint = new Vector3i(0, 64, 0);
    @Builder.Default
    SemVersion minimumCompatibleClientVersion = ProtocolInfo.MINECRAFT_VERSION;
    @Builder.Default
    boolean LANBroadcast = true;
    @Builder.Default
    boolean LANBroadcastIntent = true;
    @Builder.Default
    long lastPlayed = 0L;
    @Builder.Default
    String biomeOverride = "";
    @Builder.Default
    boolean centerMapsToOrigin = false;
    @Builder.Default
    boolean confirmedPlatformLockedContent = false;
    @Builder.Default
    @Language("JSON")
    String flatWorldLayers = """
            {
              "biome_id": 1,
              "block_layers": [
                {
                  "block_name": "minecraft:bedrock",
                  "count": 1
                },
                {
                  "block_name": "minecraft:dirt",
                  "count": 2
                },
                {
                  "block_name": "minecraft:grass",
                  "count": 1
                }
              ],
              "encoding_version": 6,
              "structure_options": null,
              "world_version": "version.post_1_18"
            }
            """;
    @Builder.Default
    int generator = 1;
    @Builder.Default
    String inventoryVersion = ProtocolInfo.getMinecraftVersionStr();
    @Builder.Default
    boolean multiplayerGame = true;
    @Builder.Default
    boolean multiplayerGameIntent = false;
    @Builder.Default
    int netherScale = 8;
    @Builder.Default
    int networkVersion = ProtocolInfo.PACKET_CODEC.getProtocolVersion();
    @Builder.Default
    int platform = 2;
    @Builder.Default
    int platformBroadcastIntent = 0;
    @Builder.Default
    long randomSeed = 0L;
    @Builder.Default
    boolean spawnV1Villagers = false;
    @Builder.Default
    int storageVersion = 10;
    @Builder.Default
    int worldVersion = 1;
    @Builder.Default
    int XBLBroadcastIntent = 0;
    @Builder.Default
    Abilities abilities = Abilities.builder().build();
    @Builder.Default
    String baseGameVersion = "*";
    @Builder.Default
    boolean bonusChestEnabled = false;
    @Builder.Default
    boolean bonusChestSpawned = false;
    @Builder.Default
    boolean cheatsEnabled = false;
    @Builder.Default
    boolean commandsEnabled = true;
    @Builder.Default
    long currentTick = 0L;
    @Builder.Default
    int daylightCycle = 0;
    @Builder.Default
    int editorWorldType = 0;
    @Builder.Default
    int eduOffer = 0;
    @Builder.Default
    boolean educationFeaturesEnabled = false;
    @Builder.Default
    Experiments experiments = Experiments.builder().build();
    @Builder.Default
    boolean hasBeenLoadedInCreative = true;
    @Builder.Default
    boolean hasLockedBehaviorPack = false;
    @Builder.Default
    boolean hasLockedResourcePack = false;
    @Builder.Default
    boolean immutableWorld = false;
    @Builder.Default
    boolean isCreatedInEditor = false;
    @Builder.Default
    boolean isExportedFromEditor = false;
    @Builder.Default
    boolean isFromLockedTemplate = false;
    @Builder.Default
    boolean isFromWorldTemplate = false;
    @Builder.Default
    boolean isRandomSeedAllowed = false;
    @Builder.Default
    boolean isSingleUseWorld = false;
    @Builder.Default
    boolean isWorldTemplateOptionLocked = false;
    @Builder.Default
    SemVersion lastOpenedWithVersion = ProtocolInfo.MINECRAFT_VERSION;
    @Builder.Default
    float lightningLevel = 0.0f;
    @Builder.Default
    int lightningTime = 0;
    @Builder.Default
    int limitedWorldDepth = 16;
    @Builder.Default
    int limitedWorldWidth = 16;
    @Builder.Default
    int permissionsLevel = 0;
    @Builder.Default
    int playerPermissionsLevel = 1;
    @Builder.Default
    int playersSleepingPercentage = 100;
    @Builder.Default
    String prid = "";
    @Builder.Default
    float rainLevel = 0.0f;
    @Builder.Default
    int rainTime = 0;
    @Builder.Default
    int randomTickSpeed = 1;
    @Builder.Default
    boolean recipesUnlock = false;
    @Builder.Default
    boolean requiresCopiedPackRemovalCheck = false;
    @Builder.Default
    int serverChunkTickRange = 4;
    @Builder.Default
    boolean spawnMobs = true;
    @Builder.Default
    boolean startWithMapEnabled = false;
    @Builder.Default
    boolean texturePacksRequired = false;
    @Builder.Default
    boolean useMsaGamertagsOnly = true;
    @Builder.Default
    long worldStartCount = 0L;
    @Builder.Default
    WorldPolicies worldPolicies = new WorldPolicies();

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        var event = new DifficultyChangeEvent(this.world, this.difficulty, difficulty);
        event.call();
        if (event.isCancelled()) return;

        this.difficulty = event.getNewDifficulty();
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        time = rollbackTime(time);

        var event = new TimeChangeEvent(this.world, this.time, time);
        event.call();
        if (event.isCancelled()) return;

        this.time = event.getNewTime();
        sendTime(this.world.getPlayers());
    }

    public void sendTime(Collection<EntityPlayer> players) {
        players.forEach(this::sendTime);
    }

    public void sendTime(EntityPlayer player) {
        var setTimePk = new SetTimePacket();
        setTimePk.setTime((int) time);
        player.sendPacket(setTimePk);
    }

    public void addTime(long amount) {
        setTime(this.time + amount);
    }

    protected long rollbackTime(long time) {
        if (time < WorldData.TIME_DAY || time > WorldData.TIME_FULL) {
            return WorldData.TIME_DAY;
        }
        return time;
    }

    public void setCurrentTick(long currentTick) {
        this.currentTick = currentTick;
    }

    public void setGameRule(GameRule gameRule, Object o) {
        var event = new GameRuleChangeEvent(this.world, gameRule, getGameRule(gameRule), o);
        event.call();
        if (event.isCancelled()) return;

        this.gameRules.put(gameRule, event.getNewValue());
    }

    public <V> V getGameRule(GameRule gameRule) {
        return gameRules.get(gameRule);
    }

    public Vector3ic getSpawnPoint() {
        return spawnPoint;
    }

    public void setSpawnPoint(Vector3ic spawnPoint) {
        var event = new SpawnPointChangeEvent(this.world, this.spawnPoint, spawnPoint);
        event.call();
        if (event.isCancelled()) return;

        this.spawnPoint = event.getNewPos();
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Value
    @Builder
    public static class Abilities {
        @Builder.Default
        boolean attackMobs = true;

        @Builder.Default
        boolean attackPlayers = true;

        @Builder.Default
        boolean build = true;

        @Builder.Default
        boolean doorsAndSwitches = true;

        @Builder.Default
        float flySpeed = 0.05f;

        @Builder.Default
        boolean flying = false;

        @Builder.Default
        boolean instaBuild = false;

        @Builder.Default
        boolean invulnerable = false;

        @Builder.Default
        boolean lightning = false;

        @Builder.Default
        boolean mayFly = false;

        @Builder.Default
        boolean mine = true;

        @Builder.Default
        boolean op = false;

        @Builder.Default
        boolean openContainers = true;

        @Builder.Default
        boolean teleport = false;

        @Builder.Default
        float walkSpeed = 0.1f;
    }

    @Value
    @Builder
    public static class Experiments {
        @Builder.Default
        boolean experimentsEverUsed = false;
        @Builder.Default
        boolean savedWithToggledExperiments = false;
        @Builder.Default
        boolean cameras = false;
        @Builder.Default
        boolean dataDrivenBiomes = false;
        @Builder.Default
        boolean dataDrivenItems = false;
        @Builder.Default
        boolean experimentalMolangFeatures = false;
        @Builder.Default
        boolean gametest = false;
        @Builder.Default
        boolean upcomingCreatorFeatures = false;
        @Builder.Default
        boolean villagerTradesRebalance = false;
    }

    @Value
    @Builder
    public static class WorldPolicies {}
}
