package org.allaymc.api.entity.component;

/**
 * {@code EntityParallelTickComponent} exposes the entity work executed during
 * a dimension's parallel tick phase. The server component dispatches an
 * internal parallel-tick event to participating components, allowing AI work
 * to run before the later sequential entity tick processes queued world
 * actions.
 *
 * @author daoge_cmd
 */
public interface EntityParallelTickComponent extends EntityComponent {
    /**
     * Runs the component work assigned to this entity's parallel tick phase.
     * Implementations must keep world mutations that require the sequential
     * phase out of this work and defer them to the appropriate tick
     * processing.
     */
    void parallelTick();
}
