package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCopperLanternStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCopperLanternStackImpl extends ItemStackImpl implements ItemCopperLanternStack {
    public ItemCopperLanternStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
