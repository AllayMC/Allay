package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemInkSacStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemInkSacStackImpl extends ItemStackImpl implements ItemInkSacStack {
    public ItemInkSacStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
