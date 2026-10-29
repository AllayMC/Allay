package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemChiseledCinnabarStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemChiseledCinnabarStackImpl extends ItemStackImpl implements ItemChiseledCinnabarStack {
    public ItemChiseledCinnabarStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
