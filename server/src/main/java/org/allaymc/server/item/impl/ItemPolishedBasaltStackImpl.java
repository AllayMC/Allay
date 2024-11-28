package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPolishedBasaltStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemPolishedBasaltStackImpl extends ItemStackImpl implements ItemPolishedBasaltStack {
    public ItemPolishedBasaltStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
