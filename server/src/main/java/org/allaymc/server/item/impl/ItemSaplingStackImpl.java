package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSaplingStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemSaplingStackImpl extends ItemStackImpl implements ItemSaplingStack {
    public ItemSaplingStackImpl(ItemStackInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
