package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPinkDyeStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemPinkDyeStackImpl extends ItemStackImpl implements ItemPinkDyeStack {
    public ItemPinkDyeStackImpl(ItemStackInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
