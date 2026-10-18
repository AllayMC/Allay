package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPinkCushionStack;
import org.allaymc.server.component.ComponentProvider;

public class ItemPinkCushionStackImpl extends ItemStackImpl implements ItemPinkCushionStack {
    public ItemPinkCushionStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
