package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemGoldBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemGoldBlockStackImpl extends ItemStackImpl implements ItemGoldBlockStack {
    public ItemGoldBlockStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
