package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemHoneycombBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemHoneycombBlockStackImpl extends ItemStackImpl implements ItemHoneycombBlockStack {
    public ItemHoneycombBlockStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
