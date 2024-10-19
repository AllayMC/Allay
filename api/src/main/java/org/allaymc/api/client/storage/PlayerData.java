package org.allaymc.api.client.storage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.server.Server;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3i;

import static org.allaymc.api.utils.AllayNbtUtils.writeVector3f;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
@Builder
public class PlayerData {
    // EntityPlayer's nbt which can be generated through method EntityPlayer#saveNBT()
    protected NbtMap nbt;

    // The following fields are not included in the return object of method EntityPlayer#saveNBT()
    // We should store the world and dimension information of the player
    // because the player data is not stored in chunk like other entities.
    // Without these information, we can't know which world and dimension the player is in.
    protected String world;
    protected int dimension;

    public static PlayerData createEmpty() {
        var server = Server.getInstance();
        var globalSpawnPoint = server.getWorldPool().getGlobalSpawnPoint();
        var builder = NbtMap.builder();
        writeVector3f(builder, "Pos", "x", "y", "z", server.getWorldPool().getGlobalSpawnPointVec3f());
        var worldName = globalSpawnPoint.dimension().getWorld().getWorldData().getName();
        var dimId = globalSpawnPoint.dimension().getDimensionInfo().dimensionId();
        return builder()
                .nbt(builder.build())
                .world(worldName)
                .dimension(dimId)
                .build();
    }

    public static PlayerData fromNBT(NbtMap nbt) {
        var builder = builder();
        builder.nbt(nbt.getCompound("NBT"))
                .world(nbt.getString("World"))
                .dimension(nbt.getInt("Dimension"));
        return builder.build();
    }

    public NbtMap toNBT() {
        var builder = NbtMap.builder()
                .putCompound("NBT", nbt)
                .putString("World", world)
                .putInt("Dimension", dimension);
        return builder.build();
    }
}
