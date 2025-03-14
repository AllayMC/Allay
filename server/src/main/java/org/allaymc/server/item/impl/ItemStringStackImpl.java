package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemStringStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemStringStackImpl extends ItemStackImpl implements ItemStringStack {
    public ItemStringStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
