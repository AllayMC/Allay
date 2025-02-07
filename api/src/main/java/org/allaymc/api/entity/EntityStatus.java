package org.allaymc.api.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the status of an entity.
 *
 * @author daoge_cmd
 */
public enum EntityStatus {
    /**
     * The entity is despawned.
     */
    DESPAWNED(false),
    /**
     * The entity will be spawn in the next tick.
     */
    SPAWNED_NEXT_TICK(false, DESPAWNED),
    /**
     * The entity is alive.
     */
    ALIVE(true, SPAWNED_NEXT_TICK),
    /**
     * The entity is dead.
     */
    DEAD(true, ALIVE),
    /**
     * The entity will be despawned in the next tick.
     */
    DESPAWNED_NEXT_TICK(false, DEAD, ALIVE);

    @Getter
    private final boolean spawned;
    /**
     * The possible previous statuses of the entity. Can be {@code null} if previous status is not exist.
     */
    @Getter
    private final Set<EntityStatus> previousStatuses;

    EntityStatus(boolean spawned, EntityStatus... previousStatuses) {
        this.spawned = spawned;
        this.previousStatuses = new HashSet<>();
        this.previousStatuses.addAll(Arrays.asList(previousStatuses));
    }
}
