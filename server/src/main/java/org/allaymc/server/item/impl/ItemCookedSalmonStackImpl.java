package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.interfaces.ItemCookedSalmonStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCookedSalmonStackImpl extends ItemStackImpl implements ItemCookedSalmonStack {
    @Delegate
    private ItemEdibleComponent edibleComponent;

    public ItemCookedSalmonStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
