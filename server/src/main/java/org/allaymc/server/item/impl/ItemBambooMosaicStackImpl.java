package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBambooMosaicStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemBambooMosaicStackImpl extends ItemStackImpl implements ItemBambooMosaicStack {
    public ItemBambooMosaicStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
