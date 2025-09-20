package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemArmsUpPotterySherdStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemArmsUpPotterySherdStackImpl extends ItemStackImpl implements ItemArmsUpPotterySherdStack {
    public ItemArmsUpPotterySherdStackImpl(ItemStackInitInfo initInfo,
                                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
