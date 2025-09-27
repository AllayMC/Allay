package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMossyCobblestoneStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemMossyCobblestoneStackImpl extends ItemStackImpl implements ItemMossyCobblestoneStack {
    public ItemMossyCobblestoneStackImpl(ItemStackInitInfo initInfo,
                                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
