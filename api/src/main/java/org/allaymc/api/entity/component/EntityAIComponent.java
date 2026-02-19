package org.allaymc.api.entity.component;

import org.allaymc.api.entity.ai.behaviorgroup.BehaviorGroup;
import org.allaymc.api.entity.ai.memory.MemoryStorage;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.joml.Vector3dc;

/**
 * Component that provides AI behavior to entities.
 *
 * @author daoge_cmd
 */
public interface EntityAIComponent extends EntityComponent {

    /**
     * Get the current behavior group.
     *
     * @return the behavior group
     */
    BehaviorGroup getBehaviorGroup();

    /**
     * Set the behavior group for this entity.
     *
     * @param behaviorGroup the behavior group
     */
    void setBehaviorGroup(BehaviorGroup behaviorGroup);

    /**
     * Get the memory storage of the current behavior group.
     *
     * @return the memory storage
     */
    default MemoryStorage getMemoryStorage() {
        return getBehaviorGroup().getMemoryStorage();
    }

    /**
     * Get the movement speed of this entity.
     *
     * @return the movement speed
     */
    default float getMovementSpeed() {
        return getMemoryStorage().get(MemoryTypes.MOVEMENT_SPEED);
    }

    /**
     * Set the movement speed of this entity.
     *
     * @param speed the movement speed
     */
    default void setMovementSpeed(float speed) {
        getMemoryStorage().put(MemoryTypes.MOVEMENT_SPEED, speed);
    }

    default Vector3dc getLookTarget() {
        return getMemoryStorage().get(MemoryTypes.LOOK_TARGET);
    }

    default void setLookTarget(Vector3dc target) {
        getMemoryStorage().put(MemoryTypes.LOOK_TARGET, target);
    }

    default Vector3dc getMoveTarget() {
        return getMemoryStorage().get(MemoryTypes.MOVE_TARGET);
    }

    default void setMoveTarget(Vector3dc target) {
        getMemoryStorage().put(MemoryTypes.MOVE_TARGET, target);
    }

    default Vector3dc getMoveDirectionStart() {
        return getMemoryStorage().get(MemoryTypes.MOVE_DIRECTION_START);
    }

    default void setMoveDirectionStart(Vector3dc start) {
        getMemoryStorage().put(MemoryTypes.MOVE_DIRECTION_START, start);
    }

    default Vector3dc getMoveDirectionEnd() {
        return getMemoryStorage().get(MemoryTypes.MOVE_DIRECTION_END);
    }

    default void setMoveDirectionEnd(Vector3dc end) {
        getMemoryStorage().put(MemoryTypes.MOVE_DIRECTION_END, end);
    }

    default boolean hasMoveDirection() {
        return getMoveDirectionStart() != null && getMoveDirectionEnd() != null;
    }

    default boolean shouldUpdateMoveDirection() {
        return Boolean.TRUE.equals(getMemoryStorage().get(MemoryTypes.SHOULD_UPDATE_MOVE_DIRECTION));
    }

    default void setShouldUpdateMoveDirection(boolean shouldUpdate) {
        getMemoryStorage().put(MemoryTypes.SHOULD_UPDATE_MOVE_DIRECTION, shouldUpdate);
    }

    default boolean isPitchEnabled() {
        return Boolean.TRUE.equals(getMemoryStorage().get(MemoryTypes.ENABLE_PITCH));
    }

    default void setPitchEnabled(boolean enabled) {
        getMemoryStorage().put(MemoryTypes.ENABLE_PITCH, enabled);
    }
}
