package org.allaymc.api.player;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.util.EnumSet;

import static org.allaymc.api.utils.AllayNBTUtils.writeVector2f;
import static org.allaymc.api.utils.AllayNBTUtils.writeVector3f;

/**
 * {@code PlayerData} stores a player entity's NBT together with the world,
 * dimension, and optional connection abilities needed to restore it. Player entities
 * are persisted outside ordinary chunk storage, so entity NBT alone does not
 * identify the world and dimension in which a reconnecting player belongs.
 *
 * <p>
 * The serialized dimension accepts both current namespaced identifiers and legacy
 * numeric registry identifiers. Unrecognized numeric or absent dimension data falls
 * back to the overworld; malformed string identifiers still fail validation. An
 * absent {@code abilities} value is distinct from an empty set: absence permits
 * login to derive game-mode defaults, while an explicit empty set records that no
 * abilities should be restored. Unknown stored ability names are logged and skipped
 * independently.
 *
 * @author daoge_cmd
 */
@Slf4j
@Getter
@Setter
@Builder
public class PlayerData {

    /**
     * {@code TAG_NBT} identifies the entity payload within the saved envelope.
     * {@code toNBT} and {@code fromNBT} use this key together.
     */
    protected static final String TAG_NBT = "NBT";
    /**
     * {@code TAG_WORLD} identifies the saved world display name. Login uses that
     * name to resolve the player's world independently of entity NBT.
     */
    protected static final String TAG_WORLD = "World";
    /**
     * {@code TAG_DIMENSION} identifies the dimension value. Reading accepts current
     * namespaced strings and legacy numeric registry values under this same envelope
     * key.
     */
    protected static final String TAG_DIMENSION = "Dimension";
    /**
     * {@code TAG_ABILITIES} identifies optional tracked abilities. Omitting its
     * value preserves the distinction between defaulted abilities and an explicitly
     * empty override.
     */
    protected static final String TAG_ABILITIES = "Abilities";

    /**
     * Serialized player-entity NBT payload.
     */
    protected NbtMap nbt;

    /**
     * Saved world display name.
     */
    protected String world;
    /**
     * Saved namespaced dimension identifier.
     */
    protected String dimension;
    /**
     * {@code abilities} holds explicitly saved connection abilities. {@code null}
     * means no stored override; the generated accessor exposes the mutable set
     * without copying it.
     */
    protected EnumSet<PlayerAbility> abilities;

    /**
     * Captures the controlled entity's NBT, world, dimension, and a copy of
     * connection abilities. A player without a controlled entity produces default
     * spawn data instead, and the method logs that fallback rather than returning
     * {@code null}.
     */
    public static PlayerData save(Player player) {
        var entity = player.getControlledEntity();
        // A connection without an entity has no entity NBT or destination to capture; return
        // global-spawn defaults instead of fabricating partial saved state.
        if (entity == null) {
            log.warn("Player is not controlling any entity!");
            return PlayerData.createEmpty();
        }

        return PlayerData.builder()
                .nbt(entity.saveNBT())
                .world(entity.getWorld().getWorldData().getDisplayName())
                .dimension(entity.getDimension().getDimensionType().getIdentifier().toString())
                .abilities(player.getAbilities().isEmpty() ? EnumSet.noneOf(PlayerAbility.class) : EnumSet.copyOf(player.getAbilities()))
                .build();
    }

    /**
     * Creates default player data at the server's global spawn point. The NBT
     * contains position and zero rotation, while {@code abilities} remains
     * {@code null} so later login can derive the game-mode defaults.
     */
    public static PlayerData createEmpty() {
        var server = Server.getInstance();
        var globalSpawnPoint = server.getWorldPool().getGlobalSpawnPoint();
        var builder = NbtMap.builder();
        writeVector3f(builder, "Pos", globalSpawnPoint.x(), globalSpawnPoint.y(), globalSpawnPoint.z());
        writeVector2f(builder, "Rotation", 0f, 0f);
        var worldName = globalSpawnPoint.dimension().getWorld().getWorldData().getDisplayName();
        var dimensionId = globalSpawnPoint.dimension().getDimensionType().getIdentifier().toString();
        return builder()
                .nbt(builder.build())
                .world(worldName)
                .dimension(dimensionId)
                .build();
    }

    /**
     * Restores a player-data envelope while preserving whether an ability override
     * was stored. The entity payload and world name are read directly, and
     * {@code readDimension} converts the saved dimension representation.
     *
     * <p>
     * A missing abilities tag leaves {@code abilities} as {@code null}; an existing
     * empty list produces an explicit empty set. Each unknown ability name is logged
     * and skipped so valid entries remain available rather than discarding the
     * entire stored override. Other malformed data is not caught by this method.
     */
    public static PlayerData fromNBT(NbtMap nbt) {
        var builder = builder();
        builder.nbt(nbt.getCompound(TAG_NBT))
                .world(nbt.getString(TAG_WORLD))
                .dimension(readDimension(nbt));

        if (nbt.containsKey(TAG_ABILITIES)) {
            var abilities = EnumSet.noneOf(PlayerAbility.class);
            for (var abilityName : nbt.getList(TAG_ABILITIES, NbtType.STRING)) {
                try {
                    abilities.add(PlayerAbility.valueOf(abilityName));
                } catch (IllegalArgumentException e) {
                    // A stored ability may no longer exist in this server version; retain recognized
                    // entries and preserve the explicit override rather than failing the load.
                    log.warn("Unknown stored player ability {}, ignoring it", abilityName);
                }
            }
            builder.abilities(abilities);
        }

        return builder.build();
    }

    /**
     * Serializes the entity payload, world name, and dimension identifier into an
     * NBT envelope. {@code abilities} is written only when non-{@code null}; an
     * explicit empty set is retained as an empty string list.
     */
    public NbtMap toNBT() {
        var builder = NbtMap.builder()
                .putCompound(TAG_NBT, nbt)
                .putString(TAG_WORLD, world)
                .putString(TAG_DIMENSION, dimension);

        if (abilities != null) {
            builder.putList(TAG_ABILITIES, NbtType.STRING, abilities.stream().map(Enum::name).toList());
        }

        return builder.build();
    }

    /**
     * Resolves current string dimension identifiers and legacy numeric dimension IDs
     * to a namespaced string. A string is validated by {@code Identifier}, so an
     * invalid string does not silently select the overworld.
     *
     * <p>
     * Numeric IDs use the current dimension registry, allowing old records to retain
     * their registered destination. Unknown numeric IDs, absent values, and
     * unsupported value types select {@code DimensionTypes.OVERWORLD}; this fallback
     * does not change the separately stored world name.
     */
    protected static String readDimension(NbtMap nbt) {
        var dimension = nbt.get(TAG_DIMENSION);
        // Saved records may use old numeric dimension IDs or current namespaced strings; preserve
        // recognized destinations and default only absent or unsupported forms.
        return switch (dimension) {
            case String id -> new Identifier(id).toString();
            case Number id -> {
                var dimensionType = Registries.DIMENSIONS.getByK1(id.intValue());
                yield (dimensionType != null ? dimensionType : DimensionTypes.OVERWORLD).getIdentifier().toString();
            }
            case null, default -> DimensionTypes.OVERWORLD.getIdentifier().toString();
        };
    }
}
