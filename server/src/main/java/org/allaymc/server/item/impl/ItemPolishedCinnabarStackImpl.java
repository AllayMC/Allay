package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPolishedCinnabarStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemPolishedCinnabarStackImpl extends ItemStackImpl implements ItemPolishedCinnabarStack {
    public ItemPolishedCinnabarStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
