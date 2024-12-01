package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemNetherBrickStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemNetherBrickStackImpl extends ItemStackImpl implements ItemNetherBrickStack {
    public ItemNetherBrickStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
