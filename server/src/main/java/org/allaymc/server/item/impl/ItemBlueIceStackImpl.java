package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBlueIceStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemBlueIceStackImpl extends ItemStackImpl implements ItemBlueIceStack {
    public ItemBlueIceStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
