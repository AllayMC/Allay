package org.allaymc.api.entity.ai.memory;

import org.allaymc.api.utils.identifier.Identifier;
import org.joml.Vector3dc;

/**
 * {@code MemoryTypes} defines the shared keys used by built-in AI producers and
 * consumers. The static keys coordinate observations and control inputs through
 * {@code MemoryStorage}; position keys carry world coordinates, entity
 * references use runtime IDs, and event timestamps use entity ticks. Each key's
 * supplier defines the value read before a producer stores an entry.
 *
 * @author daoge_cmd
 */
public final class MemoryTypes {

    /**
     * World position to face; default {@code null}.
     */
    public static final MemoryType<Vector3dc> LOOK_TARGET =
            new MemoryType<>(new Identifier("minecraft:look_target"));

    /**
     * World position to approach; default {@code null}.
     */
    public static final MemoryType<Vector3dc> MOVE_TARGET =
            new MemoryType<>(new Identifier("minecraft:move_target"));

    /**
     * Current movement segment origin; default {@code null}.
     */
    public static final MemoryType<Vector3dc> MOVE_DIRECTION_START =
            new MemoryType<>(new Identifier("minecraft:move_direction_start"));

    /**
     * Current movement segment destination; default {@code null}.
     */
    public static final MemoryType<Vector3dc> MOVE_DIRECTION_END =
            new MemoryType<>(new Identifier("minecraft:move_direction_end"));

    /**
     * Movement segment refresh request; default {@code false}.
     */
    public static final MemoryType<Boolean> SHOULD_UPDATE_MOVE_DIRECTION =
            new MemoryType<>(new Identifier("minecraft:should_update_move_direction"), () -> false);

    /**
     * Pitch rotation enable flag; default {@code true}.
     */
    public static final MemoryType<Boolean> ENABLE_PITCH =
            new MemoryType<>(new Identifier("minecraft:enable_pitch"), () -> true);

    /**
     * Controller movement speed value; default {@code 0.1f}.
     */
    public static final MemoryType<Float> MOVEMENT_SPEED =
            new MemoryType<>(new Identifier("minecraft:movement_speed"), () -> 0.1f);

    /**
     * Water surface lift enable flag; default {@code true}.
     */
    public static final MemoryType<Boolean> ENABLE_LIFT_FORCE =
            new MemoryType<>(new Identifier("minecraft:enable_lift_force"), () -> true);

    /**
     * Water descent force enable flag; default {@code true}.
     */
    public static final MemoryType<Boolean> ENABLE_DIVE_FORCE =
            new MemoryType<>(new Identifier("minecraft:enable_dive_force"), () -> true);

    /**
     * Entity tick of latest feeding; default {@code -1L}.
     */
    public static final MemoryType<Long> LAST_BE_FEED_TIME =
            new MemoryType<>(new Identifier("minecraft:last_be_feed_time"), () -> -1L);

    /**
     * Entity tick of latest love entry; default {@code -1L}.
     */
    public static final MemoryType<Long> LAST_IN_LOVE_TIME =
            new MemoryType<>(new Identifier("minecraft:last_in_love_time"), () -> -1L);

    /**
     * Current breeding eligibility flag; default {@code false}.
     */
    public static final MemoryType<Boolean> IS_IN_LOVE =
            new MemoryType<>(new Identifier("minecraft:is_in_love"), () -> false);

    /**
     * Entity spawn timestamp slot; default {@code 0L}.
     */
    public static final MemoryType<Long> ENTITY_SPAWN_TIME =
            new MemoryType<>(new Identifier("minecraft:entity_spawn_time"), () -> 0L);

    /**
     * Nearest feeding player runtime ID; default {@code null}.
     */
    public static final MemoryType<Long> NEAREST_FEEDING_PLAYER =
            new MemoryType<>(new Identifier("minecraft:nearest_feeding_player"));

    /**
     * Nearest sensed player runtime ID; default {@code null}.
     */
    public static final MemoryType<Long> NEAREST_PLAYER =
            new MemoryType<>(new Identifier("minecraft:nearest_player"));

    /**
     * Current retaliation target runtime ID; default {@code null}.
     */
    public static final MemoryType<Long> ATTACK_TARGET =
            new MemoryType<>(new Identifier("minecraft:attack_target"));

    /**
     * Selected breeding spouse runtime ID; default {@code null}.
     */
    public static final MemoryType<Long> ENTITY_SPOUSE =
            new MemoryType<>(new Identifier("minecraft:entity_spouse"));

    /**
     * Most recent feeder runtime ID; default {@code null}.
     */
    public static final MemoryType<Long> LAST_FEED_PLAYER =
            new MemoryType<>(new Identifier("minecraft:last_feed_player"));

    /**
     * Entity tick of latest egg spawning; default {@code 0L}.
     */
    public static final MemoryType<Long> LAST_EGG_SPAWN_TIME =
            new MemoryType<>(new Identifier("minecraft:last_egg_spawn_time"), () -> 0L);

    /**
     * Prevents instantiation of the shared built-in AI memory key holder.
     */
    private MemoryTypes() {
    }
}
