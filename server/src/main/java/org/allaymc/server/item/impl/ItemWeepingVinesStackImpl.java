package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWeepingVinesStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemWeepingVinesStackImpl extends ItemStackImpl implements ItemWeepingVinesStack {
    public ItemWeepingVinesStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
