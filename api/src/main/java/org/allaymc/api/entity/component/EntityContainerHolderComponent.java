package org.allaymc.api.entity.component;

import org.allaymc.api.container.ContainerHolder;

/**
 * {@code EntityContainerHolderComponent} combines entity component identity
 * with the container ownership contract. Implementations expose the
 * inventories attached to an entity through {@link ContainerHolder}, allowing
 * inventory users and entity-specific behavior to reach the same registered
 * container instances.
 *
 * @author daoge_cmd
 */
public interface EntityContainerHolderComponent extends ContainerHolder, EntityComponent {

}
