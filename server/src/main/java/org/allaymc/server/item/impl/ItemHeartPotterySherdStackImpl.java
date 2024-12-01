package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemHeartPotterySherdStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemHeartPotterySherdStackImpl extends ItemStackImpl implements ItemHeartPotterySherdStack {
    public ItemHeartPotterySherdStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
