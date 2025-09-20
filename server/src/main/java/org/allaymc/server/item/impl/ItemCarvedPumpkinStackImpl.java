package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCarvedPumpkinStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCarvedPumpkinStackImpl extends ItemStackImpl implements ItemCarvedPumpkinStack {
    public ItemCarvedPumpkinStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
