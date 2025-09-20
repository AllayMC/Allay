package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAnglerPotterySherdStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemAnglerPotterySherdStackImpl extends ItemStackImpl implements ItemAnglerPotterySherdStack {
    public ItemAnglerPotterySherdStackImpl(ItemStackInitInfo initInfo,
                                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
