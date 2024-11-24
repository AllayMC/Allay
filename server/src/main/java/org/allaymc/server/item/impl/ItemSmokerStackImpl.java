package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.furnace.ItemSmokerStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemSmokerStackImpl extends ItemStackImpl implements ItemSmokerStack {
    public ItemSmokerStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
