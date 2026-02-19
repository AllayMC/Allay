package org.allaymc.api.entity.ai.memory;

import org.allaymc.api.utils.identifier.Identifier;
import org.joml.Vector3dc;

/**
 * Core memory types used by the AI framework for motion control.
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

    public static final MemoryType<Boolean> ENABLE_LIFT_FORCE =
            new MemoryType<>(new Identifier("minecraft:enable_lift_force"), () -> true);

    public static final MemoryType<Boolean> ENABLE_DIVE_FORCE =
            new MemoryType<>(new Identifier("minecraft:enable_dive_force"), () -> true);

    private MemoryTypes() {
    }
}
