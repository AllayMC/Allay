package org.allaymc.api.entity.ai.memory;

import org.allaymc.api.server.Server;
import org.allaymc.api.utils.identifier.Identifier;
import org.joml.Vector3dc;

/**
 * Core memory types used by the AI framework.
 *
 * @author daoge_cmd
 */
public final class MemoryTypes {

    public static final MemoryType<Vector3dc> LOOK_TARGET =
            new MemoryType<>(new Identifier("minecraft:look_target"));

    public static final MemoryType<Vector3dc> MOVE_TARGET =
            new MemoryType<>(new Identifier("minecraft:move_target"));

    public static final MemoryType<Vector3dc> MOVE_DIRECTION_START =
            new MemoryType<>(new Identifier("minecraft:move_direction_start"));

    public static final MemoryType<Vector3dc> MOVE_DIRECTION_END =
            new MemoryType<>(new Identifier("minecraft:move_direction_end"));

    public static final MemoryType<Boolean> SHOULD_UPDATE_MOVE_DIRECTION =
            new MemoryType<>(new Identifier("minecraft:should_update_move_direction"), () -> false);

    public static final MemoryType<Boolean> ENABLE_PITCH =
            new MemoryType<>(new Identifier("minecraft:enable_pitch"), () -> true);

    public static final MemoryType<Float> MOVEMENT_SPEED =
            new MemoryType<>(new Identifier("minecraft:movement_speed"), () -> 0.1f);

    public static final MemoryType<Boolean> ENABLE_LIFT_FORCE =
            new MemoryType<>(new Identifier("minecraft:enable_lift_force"), () -> true);

    public static final MemoryType<Boolean> ENABLE_DIVE_FORCE =
            new MemoryType<>(new Identifier("minecraft:enable_dive_force"), () -> true);

    public static final MemoryType<Long> LAST_BE_FEED_TIME =
            new MemoryType<>(new Identifier("minecraft:last_be_feed_time"), () -> -1L);

    public static final MemoryType<Long> LAST_IN_LOVE_TIME =
            new MemoryType<>(new Identifier("minecraft:last_in_love_time"), () -> -1L);

    public static final MemoryType<Boolean> IS_IN_LOVE =
            new MemoryType<>(new Identifier("minecraft:is_in_love"), () -> false);

    public static final MemoryType<Long> ENTITY_SPAWN_TIME =
            new MemoryType<>(new Identifier("minecraft:entity_spawn_time"), () -> Server.getInstance().getTick());

    public static final MemoryType<Long> NEAREST_FEEDING_PLAYER =
            new MemoryType<>(new Identifier("minecraft:nearest_feeding_player"));

    public static final MemoryType<Long> NEAREST_PLAYER =
            new MemoryType<>(new Identifier("minecraft:nearest_player"));

    public static final MemoryType<Long> ENTITY_SPOUSE =
            new MemoryType<>(new Identifier("minecraft:entity_spouse"));

    public static final MemoryType<Long> LAST_FEED_PLAYER =
            new MemoryType<>(new Identifier("minecraft:last_feed_player"));

    public static final MemoryType<Long> LAST_EGG_SPAWN_TIME =
            new MemoryType<>(new Identifier("minecraft:last_egg_spawn_time"), () -> Server.getInstance().getTick());

    private MemoryTypes() {
    }
}
