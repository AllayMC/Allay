package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemClayStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemClayStackImpl extends ItemStackImpl implements ItemClayStack {
    public ItemClayStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
