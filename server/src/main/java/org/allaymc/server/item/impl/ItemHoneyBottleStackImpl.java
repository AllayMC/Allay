package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.interfaces.ItemHoneyBottleStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemHoneyBottleStackImpl extends ItemStackImpl implements ItemHoneyBottleStack {
    @Delegate
    private ItemEdibleComponent edibleComponent;

    public ItemHoneyBottleStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
