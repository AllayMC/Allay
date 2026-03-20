package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemWearableComponent;
import org.allaymc.api.item.interfaces.ItemHeadStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemHeadStackImpl extends ItemStackImpl implements ItemHeadStack {

    @Delegate
    private ItemWearableComponent wearableComponent;

    public ItemHeadStackImpl(ItemStackInitInfo initInfo,
                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
