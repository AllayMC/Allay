package org.allaymc.server.item.component;

import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.component.interfaces.ComponentProvider.SimpleComponentProvider;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.item.component.ItemComponentImplFactory;
import org.allaymc.api.item.component.common.ItemAttributes;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.server.item.component.air.ItemAirBaseComponentImpl;
import org.allaymc.server.item.component.cauldron.ItemCauldronBaseComponentImpl;
import org.allaymc.server.item.component.common.ItemAttributeComponentImpl;
import org.allaymc.server.item.component.common.ItemBaseComponentImpl;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public class AllayItemComponentImplFactory implements ItemComponentImplFactory {

    @Override
    public ComponentProvider<ItemComponent> createItemBaseComponent() {
        return new SimpleComponentProvider<ItemComponent, ItemStackInitInfo<?>>(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class);
    }

    @Override
    public ComponentProvider<ItemComponent> createItemCauldronBaseComponent() {
        return new SimpleComponentProvider<>(ItemCauldronBaseComponentImpl::new, ItemCauldronBaseComponentImpl.class);
    }

    @Override
    public ComponentProvider<ItemComponent> createItemAirBaseComponent() {
        return new SimpleComponentProvider<>(info -> new ItemAirBaseComponentImpl(), ItemAirBaseComponentImpl.class);
    }

    @Override
    public ComponentProvider<ItemComponent> createItemAttributeComponent(ItemAttributes itemAttributes) {
        return new SimpleComponentProvider<>(info -> new ItemAttributeComponentImpl(itemAttributes), ItemAttributeComponentImpl.class);
    }

    @Override
    public ComponentProvider<ItemComponent> createDefaultItemAttributeComponent() {
        return new SimpleComponentProvider<>(info -> ItemAttributeComponentImpl.ofDefault(), ItemAttributeComponentImpl.class);
    }
}
