package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemHayBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemHayBlockStackImpl extends ItemStackImpl implements ItemHayBlockStack {
    public ItemHayBlockStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
