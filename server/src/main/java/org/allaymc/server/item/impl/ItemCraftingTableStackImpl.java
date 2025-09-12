package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCraftingTableStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCraftingTableStackImpl extends ItemStackImpl implements ItemCraftingTableStack {
    public ItemCraftingTableStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
