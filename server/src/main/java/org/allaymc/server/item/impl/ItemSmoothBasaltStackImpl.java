package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSmoothBasaltStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemSmoothBasaltStackImpl extends ItemStackImpl implements ItemSmoothBasaltStack {
    public ItemSmoothBasaltStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
