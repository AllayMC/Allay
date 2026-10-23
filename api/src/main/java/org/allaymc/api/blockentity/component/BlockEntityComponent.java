package org.allaymc.api.blockentity.component;

import org.allaymc.api.component.Component;

/**
 * {@code BlockEntityComponent} marks a component that contributes behavior or
 * data to a block entity. The server's block-entity type builder uses this
 * specialization to collect providers, while the general component system
 * performs dependency injection and lifecycle initialization for the composed
 * block entity.
 *
 * @author daoge_cmd
 */
public interface BlockEntityComponent extends Component {
}
