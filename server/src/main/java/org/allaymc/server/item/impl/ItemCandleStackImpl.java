package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCandleStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemCandleStackImpl extends ItemStackImpl implements ItemCandleStack {
    public ItemCandleStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
