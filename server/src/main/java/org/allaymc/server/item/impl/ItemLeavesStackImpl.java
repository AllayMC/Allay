package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLeavesStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemLeavesStackImpl extends ItemStackImpl implements ItemLeavesStack {
    public ItemLeavesStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
