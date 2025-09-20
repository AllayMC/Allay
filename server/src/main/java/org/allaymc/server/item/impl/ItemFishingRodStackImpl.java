package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFishingRodStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemFishingRodStackImpl extends ItemStackImpl implements ItemFishingRodStack {
    public ItemFishingRodStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
