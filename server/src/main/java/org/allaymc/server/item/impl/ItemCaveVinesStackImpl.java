package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCaveVinesStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemCaveVinesStackImpl extends ItemStackImpl implements ItemCaveVinesStack {
    public ItemCaveVinesStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
