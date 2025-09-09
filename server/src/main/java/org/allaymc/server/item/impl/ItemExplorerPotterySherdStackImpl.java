package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemExplorerPotterySherdStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemExplorerPotterySherdStackImpl extends ItemStackImpl implements ItemExplorerPotterySherdStack {
    public ItemExplorerPotterySherdStackImpl(ItemStackInitInfo initInfo,
                                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
