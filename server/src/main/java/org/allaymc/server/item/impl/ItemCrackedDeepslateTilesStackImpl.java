package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCrackedDeepslateTilesStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemCrackedDeepslateTilesStackImpl extends ItemStackImpl implements ItemCrackedDeepslateTilesStack {
    public ItemCrackedDeepslateTilesStackImpl(ItemStackInitInfo initInfo,
                                              List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
