package org.allaymc.api.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * {@code EntityState} defines lifecycle states and the predecessor states
 * accepted by entity state changes. {@code spawned} distinguishes entities
 * still represented as spawned, including dead entities, from pending or
 * completed removal states; an empty predecessor set permits transitions from
 * any current state.
 *
 * @author daoge_cmd
 */
public enum EntityState {
    /**
     * Entity removed from the dimension; this state accepts any predecessor.
     */
    DESPAWNED(false),
    /**
     * Entity queued for spawning after an initial despawned state.
     */
    SPAWNED_LATER(false, DESPAWNED),
    /**
     * Spawned living state entered after the queued spawn completes.
     */
    ALIVE(true, SPAWNED_LATER),
    /**
     * Spawned dead state entered from the living state.
     */
    DEAD(true, ALIVE),
    /**
     * Entity queued for removal after a living or dead state.
     */
    DESPAWNED_LATER(false, DEAD, ALIVE);

    /**
     * Whether entity lifecycle queries treat this state as spawned.
     */
    @Getter
    private final boolean spawned;
    /**
     * {@code previousStates} contains permitted predecessors. The generated
     * getter exposes the mutable set directly, so modifying it changes the
     * transitions accepted by {@code EntityBaseComponentImpl.setState}.
     */
    @Getter
    private final Set<EntityState> previousStates;

    /**
     * Creates a lifecycle state with its spawned flag and a mutable
     * predecessor set. An empty {@code previousStates} array gives the
     * transition checker no predecessor restriction, rather than forbidding
     * every transition.
     */
    EntityState(boolean spawned, EntityState... previousStates) {
        this.spawned = spawned;
        this.previousStates = new HashSet<>();
        this.previousStates.addAll(Arrays.asList(previousStates));
    }
}
