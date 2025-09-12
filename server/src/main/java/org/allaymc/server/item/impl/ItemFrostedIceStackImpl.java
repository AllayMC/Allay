package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFrostedIceStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemFrostedIceStackImpl extends ItemStackImpl implements ItemFrostedIceStack {
    public ItemFrostedIceStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
