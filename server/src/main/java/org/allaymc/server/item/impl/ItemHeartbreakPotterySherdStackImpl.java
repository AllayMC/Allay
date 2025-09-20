package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemHeartbreakPotterySherdStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemHeartbreakPotterySherdStackImpl extends ItemStackImpl implements ItemHeartbreakPotterySherdStack {
    public ItemHeartbreakPotterySherdStackImpl(ItemStackInitInfo initInfo,
                                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
