package org.allaymc.api.client.storage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.server.Server;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import static org.allaymc.api.utils.AllayNbtUtils.writeVector3f;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
@Builder
public class PlayerData {
    // EntityPlayer's NBT which can be generated through method EntityPlayer#saveNBT()
    protected NbtMap playerNBT;
    // The following fields are not included in the return object of method EntityPlayer#saveNBT()
    protected String currentWorldName;
    protected int currentDimensionId;
    // Can be null
    protected Vector3ic spawnPoint;
    // Can be null
    protected String spawnPointWorldName;
    protected int spawnPointDimensionId;

    public static PlayerData createEmpty() {
        var server = Server.getInstance();
        var globalSpawnPoint = server.getWorldPool().getGlobalSpawnPoint();
        var builder = NbtMap.builder();
        writeVector3f(builder, "Pos", "x", "y", "z", server.getWorldPool().getGlobalSpawnPointVec3f());
        var worldName = globalSpawnPoint.dimension().getWorld().getWorldData().getName();
        var dimId = globalSpawnPoint.dimension().getDimensionInfo().dimensionId();
        return builder()
                .playerNBT(builder.build())
                .currentWorldName(worldName)
                .currentDimensionId(dimId)
                .spawnPoint(globalSpawnPoint)
                .spawnPointWorldName(worldName)
                .spawnPointDimensionId(dimId)
                .build();
    }

    public static PlayerData fromNBT(NbtMap nbt) {
        var builder = builder();
        builder.playerNBT(nbt.getCompound("PlayerNBT"))
                .currentWorldName(nbt.getString("CurrentWorldName"))
                .currentDimensionId(nbt.getInt("CurrentDimensionId"));
        if (nbt.containsKey("SpawnPointX")) {
            int spx = nbt.getInt("SpawnPointX");
            int spy = nbt.getInt("SpawnPointY");
            int spz = nbt.getInt("SpawnPointZ");
            builder.spawnPoint(new Vector3i(spx, spy, spz))
                    .spawnPointWorldName(nbt.getString("SpawnPointWorldName"))
                    .spawnPointDimensionId(nbt.getInt("SpawnPointDimensionId"));
        }
        return builder.build();
    }

    public NbtMap toNBT() {
        var builder = NbtMap.builder()
                .putCompound("PlayerNBT", playerNBT)
                .putString("CurrentWorldName", currentWorldName)
                .putInt("CurrentDimensionId", currentDimensionId);
        if (spawnPoint != null) {
            builder.putInt("SpawnPointX", spawnPoint.x())
                    .putInt("SpawnPointY", spawnPoint.y())
                    .putInt("SpawnPointZ", spawnPoint.z())
                    .putString("SpawnPointWorldName", spawnPointWorldName)
                    .putInt("SpawnPointDimensionId", spawnPointDimensionId);
        }
        return builder.build();
    }
}
