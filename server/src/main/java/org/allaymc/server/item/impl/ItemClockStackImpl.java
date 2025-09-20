package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemClockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemClockStackImpl extends ItemStackImpl implements ItemClockStack {
    public ItemClockStackImpl(ItemStackInitInfo initInfo,
                              List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
