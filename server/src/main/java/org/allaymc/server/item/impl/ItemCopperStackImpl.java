package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCopperStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemCopperStackImpl extends ItemStackImpl implements ItemCopperStack {
    public ItemCopperStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
