package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRedstoneLampStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemRedstoneLampStackImpl extends ItemStackImpl implements ItemRedstoneLampStack {
    public ItemRedstoneLampStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
