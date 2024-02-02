package org.allaymc.api.world;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.allaymc.api.datastruct.SemVersion;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.gamerule.GameRules;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.jetbrains.annotations.ApiStatus;
import org.joml.Vector3i;
import org.joml.Vector3ic;

@Getter
@Builder
public class WorldData {
    @Builder.Default
    String biomeOverride = "";
    @Builder.Default
    boolean centerMapsToOrigin = false;
    @Builder.Default
    boolean confirmedPlatformLockedContent = false;
    @Builder.Default
    Difficulty difficulty = Difficulty.from(1);
    @Builder.Default
    String flatWorldLayers = """
            {"biome_id":1,"block_layers":[{"block_name":"minecraft:bedrock","count":1},{"block_name":"minecraft:dirt","count":2},{"block_name":"minecraft:grass","count":1}],"encoding_version":6,"structure_options":null,"world_version":"version.post_1_18"}
            """;
    @Builder.Default
    boolean forceGameType = false;
    @Builder.Default
    org.cloudburstmc.protocol.bedrock.data.GameType gameType = GameType.from(1);
    @Builder.Default
    int generator = 1;
    @Builder.Default
    String inventoryVersion = "1.20.40";
    @Builder.Default
    boolean LANBroadcast = true;
    @Builder.Default
    boolean LANBroadcastIntent = true;
    @Builder.Default
    long lastPlayed = 0L;
    @Builder.Default
    String name = "Bedrock level";
    @Builder.Default
    org.joml.Vector3ic limitedWorldOriginPoint = new Vector3i(0, 64, 0);
    @Builder.Default
    SemVersion minimumCompatibleClientVersion = new SemVersion(
            1,
            20,
            40,
            0,
            0
    );
    @Builder.Default
    boolean multiplayerGame = true;
    @Builder.Default
    boolean multiplayerGameIntent = false;
    @Builder.Default
    int netherScale = 8;
    @Builder.Default
    int networkVersion = 622;
    @Builder.Default
    int platform = 2;
    @Builder.Default
    int platformBroadcastIntent = 0;
    @Builder.Default
    long randomSeed = 1811906518383890446L;
    @Builder.Default
    boolean spawnV1Villagers = false;
    @Builder.Default
    org.joml.Vector3ic spawnPoint = new Vector3i(0, 64, 0);
    @Builder.Default
    int storageVersion = 10;
    @Builder.Default
    long time = 0L;
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
    GameRules gameRules = new GameRules();
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
    SemVersion lastOpenedWithVersion = new SemVersion(
            1,
            20,
            40,
            1,
            0
    );
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

    World world;

    public synchronized void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public synchronized GameType getGameType() {
        return gameType;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized Difficulty getDifficulty() {
        return difficulty;
    }

    public synchronized void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public synchronized long getTime() {
        return time;
    }

    public synchronized void setTime(long time) {
        this.time = time;
    }

    public synchronized void addTime() {
        this.time++;
    }

    public synchronized void addTime(int value) {
        this.time += value;
    }

    public synchronized void setCurrentTick(long currentTick) {
        this.currentTick = currentTick;
    }

    public synchronized void setGameRule(GameRule gameRule, Object o) {
        this.gameRules.put(gameRule, o);
    }

    public synchronized <V> V getGameRule(GameRule gameRule) {
        return gameRules.get(gameRule);
    }

    /**
     * The overworld default spawn point
     */
    public synchronized Vector3ic getSpawnPoint() {
        return spawnPoint;
    }

    public synchronized void setSpawnPoint(Vector3ic spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    @ApiStatus.Internal
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
    public static class WorldPolicies {
    }
}
