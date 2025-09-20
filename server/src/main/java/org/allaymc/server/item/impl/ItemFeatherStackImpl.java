package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFeatherStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemFeatherStackImpl extends ItemStackImpl implements ItemFeatherStack {
    public ItemFeatherStackImpl(ItemStackInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
