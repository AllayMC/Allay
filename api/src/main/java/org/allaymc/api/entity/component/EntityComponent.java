package org.allaymc.api.entity.component;

import org.allaymc.api.component.Component;

/**
 * {@code EntityComponent} marks a component that contributes state or behavior
 * to an entity. Entity-specific component interfaces extend this marker
 * alongside their functional contracts, allowing component-backed entity types
 * to expose the capabilities assembled by their component provider.
 *
 * @author daoge_cmd
 */
public interface EntityComponent extends Component {
}
