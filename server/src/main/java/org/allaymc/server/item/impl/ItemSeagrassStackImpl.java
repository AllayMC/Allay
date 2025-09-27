package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSeagrassStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemSeagrassStackImpl extends ItemStackImpl implements ItemSeagrassStack {
    public ItemSeagrassStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
