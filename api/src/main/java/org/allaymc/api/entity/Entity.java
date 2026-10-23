package org.allaymc.api.entity;

import org.allaymc.api.entity.component.EntityBaseComponent;

/**
 * {@code Entity} represents a component-backed object that exists in a world
 * dimension. The inherited {@link EntityBaseComponent} contract supplies
 * identity, lifecycle, location, persistence, viewer synchronization, and
 * interactions that are common to specialized entity interfaces.
 *
 * @author daoge_cmd
 */
public interface Entity extends EntityBaseComponent {
}
