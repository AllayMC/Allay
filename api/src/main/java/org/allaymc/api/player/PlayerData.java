package org.allaymc.api.player;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.server.Server;
import org.cloudburstmc.nbt.NbtMap;

import static org.allaymc.api.utils.AllayNBTUtils.writeVector3f;

/**
 * PlayerData represents the entry stores in {@link PlayerStorage}. It includes the player's nbt and the world & dimension
 * that the player is currently in.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@Builder
public class PlayerData {

    protected static final String TAG_NBT = "NBT";
    protected static final String TAG_WORLD = "World";
    protected static final String TAG_DIMENSION = "Dimension";

    // EntityPlayer's nbt, which can be generated through the method EntityPlayer#saveNBT()
    protected NbtMap nbt;

    // The following fields are not included in the return object of method EntityPlayer#saveNBT()
    // We should store the world and dimension information of the player, because the player data
    // is not stored in chunk like other entities. Without this information, we can't know which
    // world and dimension the player is in.
    protected String world;
    protected int dimension;

    /**
     * Creates an empty player data entry.
     *
     * @return an empty player data entry
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
     * @param nbt the nbt that holds the data
     * @return a {@link PlayerData} object
     */
    public static PlayerData fromNBT(NbtMap nbt) {
        var builder = builder();
        builder.nbt(nbt.getCompound(TAG_NBT))
                .world(nbt.getString(TAG_WORLD))
                .dimension(nbt.getInt(TAG_DIMENSION));
        return builder.build();
    }

    /**
     * Saves this {@link PlayerData} object to a nbt.
     *
     * @return the saved nbt
     */
    public NbtMap toNBT() {
        var builder = NbtMap.builder()
                .putCompound(TAG_NBT, nbt)
                .putString(TAG_WORLD, world)
                .putInt(TAG_DIMENSION, dimension);
        return builder.build();
    }
}
