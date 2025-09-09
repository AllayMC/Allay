package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemStrippedWarpedHyphaeStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemStrippedWarpedHyphaeStackImpl extends ItemStackImpl implements ItemStrippedWarpedHyphaeStack {
    public ItemStrippedWarpedHyphaeStackImpl(ItemStackInitInfo initInfo,
                                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
