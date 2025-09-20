package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCakeStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCakeStackImpl extends ItemStackImpl implements ItemCakeStack {
    public ItemCakeStackImpl(ItemStackInitInfo initInfo,
                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
