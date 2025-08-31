package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemStickyPistonStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemStickyPistonStackImpl extends ItemStackImpl implements ItemStickyPistonStack {
    public ItemStickyPistonStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
