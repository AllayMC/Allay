package org.allaymc.api.world;

/**
 * {@code WorldState} identifies the lifecycle phase published by a world
 * during startup and shutdown. World implementations change the state as
 * ticking starts or cleanup proceeds; consumers inspect the value to decide
 * whether to admit work or await shutdown completion.
 *
 * @author daoge_cmd
 */
public enum WorldState {
    /**
     * World tick-loop startup before {@code RUNNING} is published.
     */
    STARTING,
    /**
     * World loop phase that admits ordinary world ticks.
     */
    RUNNING,
    /**
     * World stop request awaiting or accompanying lifecycle cleanup.
     */
    STOPPING,
    /**
     * Final state published after normal world cleanup completes.
     */
    STOPPED
}
