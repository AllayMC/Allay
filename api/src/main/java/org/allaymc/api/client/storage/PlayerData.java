package org.allaymc.api.client.storage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.server.Server;
import org.cloudburstmc.nbt.NbtMap;

import static org.allaymc.api.utils.AllayNbtUtils.writeVector3f;

/**
 * PlayerData represents the entry stores in {@link PlayerStorage}. It includes the player's nbt, and the world & dimension
 * that the player is currently in.
 *
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

    /**
     * Creates an empty player data.
     *
     * @return an empty player data.
     */
    public static PlayerData createEmpty() {
        var server = Server.getInstance();
        var globalSpawnPoint = server.getWorldPool().getGlobalSpawnPoint();
        var builder = NbtMap.builder();
        writeVector3f(builder, "Pos", globalSpawnPoint.x(), globalSpawnPoint.y(), globalSpawnPoint.z());
        var worldName = globalSpawnPoint.dimension().getWorld().getWorldData().getDisplayName();
        var dimId = globalSpawnPoint.dimension().getDimensionInfo().dimensionId();
        return builder()
                .nbt(builder.build())
                .world(worldName)
                .dimension(dimId)
                .build();
    }

    /**
     * Creates a {@link PlayerData} object from a nbt.
     *
     * @param nbt the nbt that holds the data.
     * @return a {@link PlayerData} object.
     */
    public static PlayerData fromNBT(NbtMap nbt) {
        var builder = builder();
        builder.nbt(nbt.getCompound("NBT"))
                .world(nbt.getString("World"))
                .dimension(nbt.getInt("Dimension"));
        return builder.build();
    }

    /**
     * Saves this {@link PlayerData} object to a nbt.
     *
     * @return the saved nbt.
     */
    public NbtMap toNBT() {
        var builder = NbtMap.builder()
                .putCompound("NBT", nbt)
                .putString("World", world)
                .putInt("Dimension", dimension);
        return builder.build();
    }
}
