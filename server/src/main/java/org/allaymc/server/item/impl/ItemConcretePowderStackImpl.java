package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemConcretePowderStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemConcretePowderStackImpl extends ItemStackImpl implements ItemConcretePowderStack {
    public ItemConcretePowderStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
