package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAzureBluetStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemAzureBluetStackImpl extends ItemStackImpl implements ItemAzureBluetStack {
    public ItemAzureBluetStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
