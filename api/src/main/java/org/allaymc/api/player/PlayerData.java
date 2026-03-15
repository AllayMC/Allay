package org.allaymc.api.player;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.server.Server;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.util.EnumSet;

import static org.allaymc.api.utils.AllayNBTUtils.writeVector3f;

/**
 * PlayerData represents the entry stores in {@link PlayerStorage}. It includes the player's nbt, the world, and
 * the dimension that the player is currently in.
 *
 * @author daoge_cmd
 */
@Slf4j
@Getter
@Setter
@Builder
public class PlayerData {

    protected static final String TAG_NBT = "NBT";
    protected static final String TAG_WORLD = "World";
    protected static final String TAG_DIMENSION = "Dimension";
    protected static final String TAG_ABILITIES = "Abilities";

    // EntityPlayer's nbt, which can be generated through the method EntityPlayer#saveNBT()
    protected NbtMap nbt;

    // The following fields are not included in the return object of method EntityPlayer#saveNBT()
    // We should store the world and dimension information of the player, because the player data
    // is not stored in chunk like other entities. Without this information, we can't know which
    // world and dimension the player is in.
    protected String world;
    protected int dimension;
    protected EnumSet<PlayerAbility> abilities;

    public static PlayerData save(Player player) {
        var entity = player.getControlledEntity();
        if (entity == null) {
            log.warn("Player is not controlling any entity!");
            return PlayerData.createEmpty();
        }

        return PlayerData.builder()
                .nbt(entity.saveNBT())
                .world(entity.getWorld().getWorldData().getDisplayName())
                .dimension(entity.getDimension().getDimensionInfo().dimensionId())
                .abilities(player.getAbilities().isEmpty() ? EnumSet.noneOf(PlayerAbility.class) : EnumSet.copyOf(player.getAbilities()))
                .build();
    }

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

        if (nbt.containsKey(TAG_ABILITIES)) {
            var abilities = EnumSet.noneOf(PlayerAbility.class);
            for (var abilityName : nbt.getList(TAG_ABILITIES, NbtType.STRING)) {
                try {
                    abilities.add(PlayerAbility.valueOf(abilityName));
                } catch (IllegalArgumentException e) {
                    log.warn("Unknown stored player ability {}, ignoring it", abilityName);
                }
            }
            builder.abilities(abilities);
        }

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

        if (abilities != null) {
            builder.putList(TAG_ABILITIES, NbtType.STRING, abilities.stream().map(Enum::name).toList());
        }

        return builder.build();
    }
}
