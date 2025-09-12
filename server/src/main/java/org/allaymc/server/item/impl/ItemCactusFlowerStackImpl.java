package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCactusFlowerStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCactusFlowerStackImpl extends ItemStackImpl implements ItemCactusFlowerStack {
    public ItemCactusFlowerStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
