package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCrackedDeepslateTilesStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCrackedDeepslateTilesStackImpl extends ItemStackImpl implements ItemCrackedDeepslateTilesStack {
    public ItemCrackedDeepslateTilesStackImpl(ItemStackInitInfo initInfo,
                                              List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
