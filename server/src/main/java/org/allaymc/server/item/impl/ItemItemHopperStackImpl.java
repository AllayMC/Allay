package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemItemHopperStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemItemHopperStackImpl extends ItemStackImpl implements ItemItemHopperStack {
    public ItemItemHopperStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
