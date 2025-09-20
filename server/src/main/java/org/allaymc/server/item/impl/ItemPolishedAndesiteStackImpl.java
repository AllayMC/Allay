package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPolishedAndesiteStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemPolishedAndesiteStackImpl extends ItemStackImpl implements ItemPolishedAndesiteStack {
    public ItemPolishedAndesiteStackImpl(ItemStackInitInfo initInfo,
                                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
