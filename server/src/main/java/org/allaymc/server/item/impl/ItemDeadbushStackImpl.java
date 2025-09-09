package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDeadbushStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemDeadbushStackImpl extends ItemStackImpl implements ItemDeadbushStack {
    public ItemDeadbushStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
