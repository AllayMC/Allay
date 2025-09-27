package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBambooMosaicStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemBambooMosaicStackImpl extends ItemStackImpl implements ItemBambooMosaicStack {
    public ItemBambooMosaicStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
