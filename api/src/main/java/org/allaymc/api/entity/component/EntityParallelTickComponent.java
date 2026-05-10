package org.allaymc.api.entity.component;

/**
 * Component for entities that participate in a parallel tick phase.
 *
 * @author daoge_cmd
 */
public interface EntityParallelTickComponent extends EntityComponent {
    void parallelTick();
}
