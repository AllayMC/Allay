package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRawIronBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemRawIronBlockStackImpl extends ItemStackImpl implements ItemRawIronBlockStack {
    public ItemRawIronBlockStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
