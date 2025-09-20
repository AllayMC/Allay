package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemArcherPotterySherdStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemArcherPotterySherdStackImpl extends ItemStackImpl implements ItemArcherPotterySherdStack {
    public ItemArcherPotterySherdStackImpl(ItemStackInitInfo initInfo,
                                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
