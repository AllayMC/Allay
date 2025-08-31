package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemItemHopperStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemItemHopperStackImpl extends ItemStackImpl implements ItemItemHopperStack {
    public ItemItemHopperStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
