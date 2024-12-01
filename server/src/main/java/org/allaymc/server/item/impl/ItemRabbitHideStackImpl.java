package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRabbitHideStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemRabbitHideStackImpl extends ItemStackImpl implements ItemRabbitHideStack {
    public ItemRabbitHideStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
