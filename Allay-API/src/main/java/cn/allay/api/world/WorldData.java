package cn.allay.api.world;

import cn.allay.api.world.gamerule.GameRules;
import lombok.Builder;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3i;
import org.joml.Vector3ic;

@Data
@Builder
public final class WorldData {
    public static WorldData DEFAULT = WorldData.builder()
            .gameRules(GameRules.DEFAULT)
            .spawnPoint(new Vector3i(0, 64, 0))
            .difficulty(Difficulty.NORMAL)
            .storageVersion(0)
            .randomSeed(114514)
            .time(0)
            .lightningTick(0)
            .rainTick(0)
            .gameType(GameType.SURVIVAL)
            .viewDistance(8)
            .tickingRadius(8)
            .levelName("Allay World")
            .generatorOptions("""
                    {"biome_id":1,"block_layers":[{"block_name":"minecraft:bedrock","count":1},{"block_name":"minecraft:dirt","count":2},{"block_name":"minecraft:grass","count":1}],"encoding_version":6,"structure_options":null,"world_version":"version.post_1_18"}
                    """)
            .dimensionInfo(DimensionInfo.OVERWORLD)
            .build();

    GameRules gameRules;
    Vector3ic spawnPoint;
    Difficulty difficulty;
    int storageVersion;
    int tickingRadius;
    int viewDistance;
    long randomSeed;
    long time;
    long lightningTick;
    long rainTick;
    GameType gameType;
    String levelName;
    String generatorOptions;
    DimensionInfo dimensionInfo;
    /**
     * 用来标识这个level.dat是不是经过Allay修改过的
     */
    boolean allay;
}

