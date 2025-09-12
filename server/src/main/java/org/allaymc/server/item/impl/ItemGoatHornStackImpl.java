package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemGoatHornStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemGoatHornStackImpl extends ItemStackImpl implements ItemGoatHornStack {
    public ItemGoatHornStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
