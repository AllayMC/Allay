package cn.allay.api.world;

import cn.allay.api.server.Server;
import cn.allay.api.world.gamerule.GameRules;
import lombok.Builder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.Ability;

import java.util.List;
import java.util.Map;

@Builder
public record WorldData(
        GameRules gameRules,
        long lightningTime,
        int Platform,
        int Difficulty,
        boolean hasLockedResourcePack,
        int GameType,
        boolean isFromWorldTemplate,
        boolean hasBeenLoadedInCreative,
        float lightningLevel,
        int XBLBroadcastIntent,
        boolean startWithMapEnabled,
        float rainLevel,
        int PlatformBroadcastIntent,
        List<Integer> lastOpenedWithVersion,
        int serverChunkTickRange,
        int WorldVersion,
        boolean educationFeaturesEnabled,
        boolean isExportedFromEditor,
        boolean isCreatedInEditor,
        int Generator,
        long RandomSeed,
        int NetworkVersion,
        Map<String, Boolean> experiments,
        boolean commandsEnabled,
        boolean spawnMobs,
        String InventoryVersion,
        boolean CenterMapsToOrigin,
        boolean LANBroadcastIntent,
        long currentTick,
        boolean isFromLockedTemplate,
        int rainTime,
        boolean LANBroadcast,
        Map<String, String> worldPolicies,
        boolean hasLockedBehaviorPack,
        boolean cheatsEnabled,
        int StorageVersion,
        List<Integer> MinimumCompatibleClientVersion,
        boolean ConfirmedPlatformLockedContent,
        boolean texturePacksRequired,
        boolean bonusChestEnabled,
        Map<Ability, Object> abilities,
        int daylightCycle,
        String baseGameVersion,
        boolean bonusChestSpawned,
        boolean MultiplayerGame,
        boolean isSingleUseWorld,
        int permissionsLevel,
        int spawnY,
        int spawnZ,
        int spawnX,
        String prid,
        int eduOffer,
        long worldStartCount,
        boolean forceGameType,
        boolean MultiplayerGameIntent,
        String LevelName,
        boolean isWorldTemplateOptionLocked,
        long Time,
        int limitedWorldDepth,
        int LimitedWorldOriginY,
        boolean requiresCopiedPackRemovalCheck,
        int LimitedWorldOriginX,
        int LimitedWorldOriginZ,
        int limitedWorldWidth,
        String BiomeOverride,
        boolean SpawnV1Villagers,
        long LastPlayed,
        int NetherScale,
        boolean useMsaGamertagsOnly,
        String FlatWorldLayers,
        boolean immutableWorld,
        int playerPermissionsLevel
) {
    public static WorldData DEFAULT = WorldData.builder()
            .WorldVersion(1)
            .lightningTime(0)
            .Platform(2)
            .Difficulty(1)
            .hasLockedResourcePack(false)
            .GameType(0)
            .isFromWorldTemplate(false)
            .hasBeenLoadedInCreative(true)
            .lightningLevel(1f)
            .XBLBroadcastIntent(2)
            .startWithMapEnabled(false)
            .rainLevel(0f)
            .PlatformBroadcastIntent(2)
            .lastOpenedWithVersion(List.of(1, 0, 0))
            .serverChunkTickRange(Server.getInstance().getServerSettings().defaultTickingRadius())
            .educationFeaturesEnabled(false)
            .isExportedFromEditor(false)
            .isCreatedInEditor(false)
            .Generator(1)
            .RandomSeed(0)
            .NetworkVersion(Server.getInstance().getNetworkServer().getCodec().getProtocolVersion())
            .experiments(Map.of())
            .spawnMobs(true)
            .InventoryVersion("")//TODO: Default Value
            .CenterMapsToOrigin(false)
            .LANBroadcastIntent(true)
            .currentTick(0)
            .isFromLockedTemplate(false)
            .rainTime(0)
            .LANBroadcast(true)
            .worldPolicies(Map.of())
            .hasLockedBehaviorPack(false)
            .cheatsEnabled(false)
            .StorageVersion(0)
            .MinimumCompatibleClientVersion(List.of(1, 0, 0))
            .ConfirmedPlatformLockedContent(false)
            .texturePacksRequired(false)
            .bonusChestEnabled(false)
            .abilities(Map.of())
            .daylightCycle(0)
            .baseGameVersion("*")
            .bonusChestSpawned(false)
            .MultiplayerGame(true)
            .isSingleUseWorld(false)
            .permissionsLevel(0)
            .spawnX(0)
            .spawnY(64)
            .spawnZ(0)
            .prid("")
            .eduOffer(0)
            .worldStartCount(0)
            .forceGameType(false)
            .MultiplayerGameIntent(false)
            .LevelName("Allay World")
            .isWorldTemplateOptionLocked(false)
            .Time(0)
            .limitedWorldDepth(16)
            .limitedWorldWidth(16)
            .LimitedWorldOriginY(32767)
            .requiresCopiedPackRemovalCheck(false)
            .LimitedWorldOriginX(-83)
            .LimitedWorldOriginZ(-86)
            .BiomeOverride("")
            .SpawnV1Villagers(false)
            .LastPlayed(0)
            .NetherScale(0)
            .useMsaGamertagsOnly(false)
            .FlatWorldLayers("""
                        {"biome_id":1,"block_layers":[{"block_name":"minecraft:bedrock","count":1},{"block_name":"minecraft:dirt","count":2},{"block_name":"minecraft:grass","count":1}],"encoding_version":6,"structure_options":null,"world_version":"version.post_1_18"}
                        """)
            .immutableWorld(false)
            .playerPermissionsLevel(1)
            .build();
}

