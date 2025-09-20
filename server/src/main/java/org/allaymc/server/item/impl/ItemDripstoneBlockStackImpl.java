package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDripstoneBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemDripstoneBlockStackImpl extends ItemStackImpl implements ItemDripstoneBlockStack {
    public ItemDripstoneBlockStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
