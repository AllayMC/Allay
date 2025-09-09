package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDirtWithRootsStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemDirtWithRootsStackImpl extends ItemStackImpl implements ItemDirtWithRootsStack {
    public ItemDirtWithRootsStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
