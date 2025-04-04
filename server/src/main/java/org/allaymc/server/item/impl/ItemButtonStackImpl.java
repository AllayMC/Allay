package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemButtonStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemButtonStackImpl extends ItemStackImpl implements ItemButtonStack {
    public ItemButtonStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
