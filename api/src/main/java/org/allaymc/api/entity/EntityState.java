package org.allaymc.api.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the state of an entity.
 *
 * @author daoge_cmd
 */
public enum EntityState {
    /**
     * The entity is despawned.
     */
    DESPAWNED(false),
    /**
     * The entity will be spawned later.
     */
    SPAWNED_LATER(false, DESPAWNED),
    /**
     * The entity is alive.
     */
    ALIVE(true, SPAWNED_LATER),
    /**
     * The entity is dead.
     */
    DEAD(true, ALIVE),
    /**
     * The entity will be despawned later.
     */
    DESPAWNED_LATER(false, DEAD, ALIVE);

    /**
     * Whether the entity is spawned.
     */
    @Getter
    private final boolean spawned;
    /**
     * The possible previous statuses of the entity. Can be {@code null} if previous state does not exist.
     */
    @Getter
    private final Set<EntityState> previousStates;

    EntityState(boolean spawned, EntityState... previousStates) {
        this.spawned = spawned;
        this.previousStates = new HashSet<>();
        this.previousStates.addAll(Arrays.asList(previousStates));
    }
}
