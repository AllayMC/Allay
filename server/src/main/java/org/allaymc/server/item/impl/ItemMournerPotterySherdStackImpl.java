package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMournerPotterySherdStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemMournerPotterySherdStackImpl extends ItemStackImpl implements ItemMournerPotterySherdStack {
    public ItemMournerPotterySherdStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
