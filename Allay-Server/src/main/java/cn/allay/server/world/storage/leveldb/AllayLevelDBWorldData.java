package cn.allay.server.world.storage.leveldb;

/**
 * Allay Project 2023/6/3
 *
 * @author Cool_Loong
 */
/*@Slf4j
public class AllayLevelDBWorldData{
    private NbtMap data;

    //LEVEL DB DEFAULT FILL
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

    public AllayLevelDBWorldData(File levelDat) {
        try (var input = new FileInputStream(levelDat)) {
            //The first 8 bytes are magic number
            input.skip(8);
            NBTInputStream readerLE = NbtUtils.createReaderLE(new ByteArrayInputStream(input.readAllBytes()));
            this.data = (NbtMap) readerLE.readTag();
            readerLE.close();
        } catch (FileNotFoundException e) {
            log.error("The level.dat file does not exist!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}*/
