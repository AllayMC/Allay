package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemStrawBedStack;
import org.allaymc.server.component.ComponentProvider;

public class ItemStrawBedStackImpl extends ItemStackImpl implements ItemStrawBedStack {
    public ItemStrawBedStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
