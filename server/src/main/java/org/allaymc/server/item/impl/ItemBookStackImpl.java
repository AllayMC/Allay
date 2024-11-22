package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBookStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemBookStackImpl extends ItemStackImpl implements ItemBookStack {
    public ItemBookStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
