package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemTrapdoorStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemTrapdoorStackImpl extends ItemStackImpl implements ItemTrapdoorStack {
    public ItemTrapdoorStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
