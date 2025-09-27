package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDyeStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemDyeStackImpl extends ItemStackImpl implements ItemDyeStack {
    public ItemDyeStackImpl(ItemStackInitInfo initInfo,
                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
