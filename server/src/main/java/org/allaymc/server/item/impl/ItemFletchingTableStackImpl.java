package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFletchingTableStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemFletchingTableStackImpl extends ItemStackImpl implements ItemFletchingTableStack {
    public ItemFletchingTableStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
