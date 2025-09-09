package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAzaleaStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemAzaleaStackImpl extends ItemStackImpl implements ItemAzaleaStack {
    public ItemAzaleaStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
