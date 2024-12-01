package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemStickyPistonStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemStickyPistonStackImpl extends ItemStackImpl implements ItemStickyPistonStack {
    public ItemStickyPistonStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
