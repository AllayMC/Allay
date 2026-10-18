package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemOrangeCushionStack;
import org.allaymc.server.component.ComponentProvider;

public class ItemOrangeCushionStackImpl extends ItemStackImpl implements ItemOrangeCushionStack {
    public ItemOrangeCushionStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
