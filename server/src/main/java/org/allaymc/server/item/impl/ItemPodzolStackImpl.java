package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPodzolStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemPodzolStackImpl extends ItemStackImpl implements ItemPodzolStack {
    public ItemPodzolStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
