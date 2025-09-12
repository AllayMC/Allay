package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCoalBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCoalBlockStackImpl extends ItemStackImpl implements ItemCoalBlockStack {
    public ItemCoalBlockStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
