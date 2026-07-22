package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSulfurStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemSulfurStackImpl extends ItemStackImpl implements ItemSulfurStack {
    public ItemSulfurStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
