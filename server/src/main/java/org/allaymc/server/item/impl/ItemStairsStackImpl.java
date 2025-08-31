package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemStairsStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemStairsStackImpl extends ItemStackImpl implements ItemStairsStack {
    public ItemStairsStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
