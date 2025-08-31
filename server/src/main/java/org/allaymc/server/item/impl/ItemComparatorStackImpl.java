package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemComparatorStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemComparatorStackImpl extends ItemStackImpl implements ItemComparatorStack {
    public ItemComparatorStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
