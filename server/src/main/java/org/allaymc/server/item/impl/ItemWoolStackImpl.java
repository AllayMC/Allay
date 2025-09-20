package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWoolStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWoolStackImpl extends ItemStackImpl implements ItemWoolStack {
    public ItemWoolStackImpl(ItemStackInitInfo initInfo,
                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
