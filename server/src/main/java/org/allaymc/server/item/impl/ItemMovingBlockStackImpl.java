package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMovingBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemMovingBlockStackImpl extends ItemStackImpl implements ItemMovingBlockStack {
    public ItemMovingBlockStackImpl(ItemStackInitInfo initInfo,
                                    List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
