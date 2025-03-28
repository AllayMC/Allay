package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDragonEggStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemDragonEggStackImpl extends ItemStackImpl implements ItemDragonEggStack {
    public ItemDragonEggStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
