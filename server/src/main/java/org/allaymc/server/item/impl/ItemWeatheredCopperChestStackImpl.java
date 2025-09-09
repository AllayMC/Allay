package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWeatheredCopperChestStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWeatheredCopperChestStackImpl extends ItemStackImpl implements ItemWeatheredCopperChestStack {
    public ItemWeatheredCopperChestStackImpl(ItemStackInitInfo initInfo,
                                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
