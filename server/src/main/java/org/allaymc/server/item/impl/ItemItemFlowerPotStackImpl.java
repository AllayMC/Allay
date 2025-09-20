package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemItemFlowerPotStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemItemFlowerPotStackImpl extends ItemStackImpl implements ItemItemFlowerPotStack {
    public ItemItemFlowerPotStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
