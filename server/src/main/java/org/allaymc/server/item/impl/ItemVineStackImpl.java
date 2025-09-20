package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemVineStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemVineStackImpl extends ItemStackImpl implements ItemVineStack {
    public ItemVineStackImpl(ItemStackInitInfo initInfo,
                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
