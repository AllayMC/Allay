package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemNetherStarStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemNetherStarStackImpl extends ItemStackImpl implements ItemNetherStarStack {
    public ItemNetherStarStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
