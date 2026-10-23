package org.allaymc.api.entity.component;

import org.allaymc.api.entity.ai.behaviorgroup.BehaviorGroup;
import org.allaymc.api.entity.ai.memory.MemoryStorage;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.joml.Vector3dc;

/**
 * {@code EntityAIComponent} exposes an entity's behavior group and the memory
 * values used by its AI controllers. The convenience methods read and write
 * the current group's shared memory, so replacing the group also changes which
 * stored targets, speed, and movement flags they access.
 *
 * @author daoge_cmd
 */
public interface EntityAIComponent extends EntityComponent {

    /**
     * Returns the behavior group currently supplying this entity's AI state.
     */
    BehaviorGroup getBehaviorGroup();

    /**
     * Replaces the behavior group used for subsequent AI work and memory
     * access. The server implementation requires its own
     * {@code BehaviorGroupImpl} implementation and binds the replacement group
     * to this entity.
     */
    void setBehaviorGroup(BehaviorGroup behaviorGroup);

    /**
     * Returns the shared memory owned by the current behavior group. Reads and
     * writes through this storage affect the same targets and flags consumed
     * by the group's sensors, behaviors, and controllers.
     */
    default MemoryStorage getMemoryStorage() {
        return getBehaviorGroup().getMemoryStorage();
    }

    /**
     * Returns the movement speed stored in the current group's memory. An
     * unset speed uses the {@code MemoryTypes.MOVEMENT_SPEED} default of
     * {@code 0.1f}; replacing the group selects that group's value.
     */
    default float getMovementSpeed() {
        return getMemoryStorage().get(MemoryTypes.MOVEMENT_SPEED);
    }

    /**
     * Stores the movement speed in the current behavior group's memory.
     */
    default void setMovementSpeed(float speed) {
        getMemoryStorage().put(MemoryTypes.MOVEMENT_SPEED, speed);
    }

    /**
     * Returns the look target from the current group's shared memory. An unset
     * entry returns {@code null}; a stored vector is returned directly rather
     * than copied for the caller.
     */
    default Vector3dc getLookTarget() {
        return getMemoryStorage().get(MemoryTypes.LOOK_TARGET);
    }

    /**
     * Stores the look target reference in the current group's memory. The
     * vector is retained without copying; {@code null} clears the usable value
     * for consumers of this memory entry.
     */
    default void setLookTarget(Vector3dc target) {
        getMemoryStorage().put(MemoryTypes.LOOK_TARGET, target);
    }

    /**
     * Returns the movement destination from the current group's shared memory.
     * An unset entry returns {@code null}; a stored vector is returned
     * directly rather than copied for the caller.
     */
    default Vector3dc getMoveTarget() {
        return getMemoryStorage().get(MemoryTypes.MOVE_TARGET);
    }

    /**
     * Stores the movement destination reference in the current group's memory.
     * The vector is retained without copying; {@code null} clears the usable
     * value for consumers of this memory entry.
     */
    default void setMoveTarget(Vector3dc target) {
        getMemoryStorage().put(MemoryTypes.MOVE_TARGET, target);
    }

    /**
     * Returns the current movement segment start from the current group's
     * shared memory. An unset entry returns {@code null}; a stored vector is
     * returned directly rather than copied for the caller.
     */
    default Vector3dc getMoveDirectionStart() {
        return getMemoryStorage().get(MemoryTypes.MOVE_DIRECTION_START);
    }

    /**
     * Stores the current movement segment start reference in the current
     * group's memory. The vector is retained without copying; {@code null}
     * clears the usable value for consumers of this memory entry.
     */
    default void setMoveDirectionStart(Vector3dc start) {
        getMemoryStorage().put(MemoryTypes.MOVE_DIRECTION_START, start);
    }

    /**
     * Returns the current movement segment end from the current group's shared
     * memory. An unset entry returns {@code null}; a stored vector is
     * returned directly rather than copied for the caller.
     */
    default Vector3dc getMoveDirectionEnd() {
        return getMemoryStorage().get(MemoryTypes.MOVE_DIRECTION_END);
    }

    /**
     * Stores the current movement segment end reference in the current group's
     * memory. The vector is retained without copying; {@code null} clears the
     * usable value for consumers of this memory entry.
     */
    default void setMoveDirectionEnd(Vector3dc end) {
        getMemoryStorage().put(MemoryTypes.MOVE_DIRECTION_END, end);
    }

    /**
     * Returns whether both endpoints of the movement segment are available.
     * Each endpoint comes from the current group's memory, and a {@code null}
     * start or end makes the segment unavailable.
     */
    default boolean hasMoveDirection() {
        return getMoveDirectionStart() != null && getMoveDirectionEnd() != null;
    }

    /**
     * Returns whether AI routing should advance or refresh the movement
     * segment. The current group's memory supplies this flag and defaults it
     * to {@code false} before a value is stored.
     */
    default boolean shouldUpdateMoveDirection() {
        return getMemoryStorage().get(MemoryTypes.SHOULD_UPDATE_MOVE_DIRECTION);
    }

    /**
     * Stores whether AI routing should update the current movement segment.
     */
    default void setShouldUpdateMoveDirection(boolean shouldUpdate) {
        getMemoryStorage().put(MemoryTypes.SHOULD_UPDATE_MOVE_DIRECTION, shouldUpdate);
    }

    /**
     * Returns whether AI look control may adjust the entity's pitch. The
     * current group's memory supplies this flag and defaults it to
     * {@code true} before a value is stored.
     */
    default boolean isPitchEnabled() {
        return getMemoryStorage().get(MemoryTypes.ENABLE_PITCH);
    }

    /**
     * Stores whether the AI look controller may change entity pitch.
     */
    default void setPitchEnabled(boolean enabled) {
        getMemoryStorage().put(MemoryTypes.ENABLE_PITCH, enabled);
    }
}
