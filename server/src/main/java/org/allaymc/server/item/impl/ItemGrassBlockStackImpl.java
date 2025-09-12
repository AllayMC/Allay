package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemGrassBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemGrassBlockStackImpl extends ItemStackImpl implements ItemGrassBlockStack {
    public ItemGrassBlockStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
