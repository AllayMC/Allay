package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMournerPotterySherdStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemMournerPotterySherdStackImpl extends ItemStackImpl implements ItemMournerPotterySherdStack {
    public ItemMournerPotterySherdStackImpl(ItemStackInitInfo initInfo,
                                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
