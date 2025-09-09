package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBarrelStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemBarrelStackImpl extends ItemStackImpl implements ItemBarrelStack {
    public ItemBarrelStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
