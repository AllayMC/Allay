package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPolishedBasaltStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemPolishedBasaltStackImpl extends ItemStackImpl implements ItemPolishedBasaltStack {
    public ItemPolishedBasaltStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
