package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAnglerPotterySherdStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemAnglerPotterySherdStackImpl extends ItemStackImpl implements ItemAnglerPotterySherdStack {
    public ItemAnglerPotterySherdStackImpl(ItemStackInitInfo initInfo,
                                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
