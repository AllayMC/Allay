package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemShortGrassStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemShortGrassStackImpl extends ItemStackImpl implements ItemShortGrassStack {
    public ItemShortGrassStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
