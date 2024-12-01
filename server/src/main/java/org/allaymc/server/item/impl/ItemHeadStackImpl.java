package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemHeadStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemHeadStackImpl extends ItemStackImpl implements ItemHeadStack {
    public ItemHeadStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
