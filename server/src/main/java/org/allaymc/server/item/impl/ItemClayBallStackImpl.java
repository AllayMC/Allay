package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemClayBallStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemClayBallStackImpl extends ItemStackImpl implements ItemClayBallStack {
    public ItemClayBallStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
