package org.allaymc.api.entity.component;

import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.entity.Entity;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public interface EntityComponentImpls {
    ComponentProvider<? extends Entity> createEntityBaseComponent();

    ComponentProvider<? extends Entity> createEntityAttributeComponent();

    ComponentProvider<? extends Entity> createEntityContainerHolderComponent();

    ComponentProvider<? extends Entity> createEntityContainerViewerComponent();
}
