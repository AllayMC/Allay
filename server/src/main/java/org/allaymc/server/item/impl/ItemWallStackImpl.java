package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWallStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWallStackImpl extends ItemStackImpl implements ItemWallStack {
    public ItemWallStackImpl(ItemStackInitInfo initInfo,
                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
