package org.allaymc.api.item.component;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.item.component.common.ItemAttributes;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public interface ItemComponentImplFactory {
    ApiInstanceHolder<ItemComponentImplFactory> FACTORY = ApiInstanceHolder.of();

    static ItemComponentImplFactory getFactory() {
        return FACTORY.get();
    }

    ComponentProvider<ItemComponent> createItemBaseComponent();

    ComponentProvider<ItemComponent> createItemCauldronBaseComponent();

    ComponentProvider<ItemComponent> createItemAirBaseComponent();

    ComponentProvider<ItemComponent> createItemAttributeComponent(ItemAttributes itemAttributes);

    ComponentProvider<ItemComponent> createDefaultItemAttributeComponent();
}
