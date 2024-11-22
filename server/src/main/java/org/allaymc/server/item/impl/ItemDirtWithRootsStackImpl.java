package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDirtWithRootsStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemDirtWithRootsStackImpl extends ItemStackImpl implements ItemDirtWithRootsStack {
    public ItemDirtWithRootsStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
