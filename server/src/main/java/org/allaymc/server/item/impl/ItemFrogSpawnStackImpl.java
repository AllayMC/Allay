package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFrogSpawnStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemFrogSpawnStackImpl extends ItemStackImpl implements ItemFrogSpawnStack {
    public ItemFrogSpawnStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
