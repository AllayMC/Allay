package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBlackDyeStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemBlackDyeStackImpl extends ItemStackImpl implements ItemBlackDyeStack {
    public ItemBlackDyeStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
