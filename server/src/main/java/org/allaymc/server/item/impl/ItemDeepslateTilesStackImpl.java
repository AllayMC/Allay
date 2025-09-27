package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDeepslateTilesStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemDeepslateTilesStackImpl extends ItemStackImpl implements ItemDeepslateTilesStack {
    public ItemDeepslateTilesStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
