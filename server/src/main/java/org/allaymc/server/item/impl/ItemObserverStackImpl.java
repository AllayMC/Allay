package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemObserverStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemObserverStackImpl extends ItemStackImpl implements ItemObserverStack {
    public ItemObserverStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
