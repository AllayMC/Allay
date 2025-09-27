package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPaintingStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemPaintingStackImpl extends ItemStackImpl implements ItemPaintingStack {
    public ItemPaintingStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
