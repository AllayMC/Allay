package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSnowStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemSnowStackImpl extends ItemStackImpl implements ItemSnowStack {
    public ItemSnowStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
