package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFireStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemFireStackImpl extends ItemStackImpl implements ItemFireStack {
    public ItemFireStackImpl(ItemStackInitInfo initInfo,
                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
