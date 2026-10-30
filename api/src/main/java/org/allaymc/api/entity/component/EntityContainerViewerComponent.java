package org.allaymc.api.entity.component;

import org.allaymc.api.container.ContainerViewer;

/**
 * {@code EntityContainerViewerComponent} combines entity component identity
 * with the container viewing contract. Specialized entities use the inherited
 * {@link ContainerViewer} operations to open and close container views, while
 * container ownership remains a separate capability supplied by a holder.
 *
 * @author daoge_cmd
 */
public interface EntityContainerViewerComponent extends ContainerViewer, EntityComponent {

}
