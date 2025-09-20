package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemGraniteStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemGraniteStackImpl extends ItemStackImpl implements ItemGraniteStack {
    public ItemGraniteStackImpl(ItemStackInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
