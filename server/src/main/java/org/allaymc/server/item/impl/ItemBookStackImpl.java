package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBookStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemBookStackImpl extends ItemStackImpl implements ItemBookStack {
    public ItemBookStackImpl(ItemStackInitInfo initInfo,
                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
