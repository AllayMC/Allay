package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAndesiteStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemAndesiteStackImpl extends ItemStackImpl implements ItemAndesiteStack {
    public ItemAndesiteStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
