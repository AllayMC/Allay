package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWeepingVinesStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWeepingVinesStackImpl extends ItemStackImpl implements ItemWeepingVinesStack {
    public ItemWeepingVinesStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
