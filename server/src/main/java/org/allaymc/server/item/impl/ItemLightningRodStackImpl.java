package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLightningRodStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemLightningRodStackImpl extends ItemStackImpl implements ItemLightningRodStack {
    public ItemLightningRodStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
