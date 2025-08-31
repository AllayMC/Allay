package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLanternStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemLanternStackImpl extends ItemStackImpl implements ItemLanternStack {
    public ItemLanternStackImpl(ItemStackInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
