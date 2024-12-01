package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemChainStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemChainStackImpl extends ItemStackImpl implements ItemChainStack {
    public ItemChainStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
