package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemArmadilloScuteStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemArmadilloScuteStackImpl extends ItemStackImpl implements ItemArmadilloScuteStack {
    public ItemArmadilloScuteStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
