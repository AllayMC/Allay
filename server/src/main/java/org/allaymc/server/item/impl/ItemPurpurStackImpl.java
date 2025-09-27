package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPurpurStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemPurpurStackImpl extends ItemStackImpl implements ItemPurpurStack {
    public ItemPurpurStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
