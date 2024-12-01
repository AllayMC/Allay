package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWoolStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemWoolStackImpl extends ItemStackImpl implements ItemWoolStack {
    public ItemWoolStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
