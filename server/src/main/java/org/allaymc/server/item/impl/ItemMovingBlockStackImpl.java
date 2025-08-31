package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMovingBlockStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemMovingBlockStackImpl extends ItemStackImpl implements ItemMovingBlockStack {
    public ItemMovingBlockStackImpl(ItemStackInitInfo initInfo,
                                    List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
