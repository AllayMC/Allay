package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLargeAmethystBudStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemLargeAmethystBudStackImpl extends ItemStackImpl implements ItemLargeAmethystBudStack {
    public ItemLargeAmethystBudStackImpl(ItemStackInitInfo initInfo,
                                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
