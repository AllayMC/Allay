package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRedMushroomBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemRedMushroomBlockStackImpl extends ItemStackImpl implements ItemRedMushroomBlockStack {
    public ItemRedMushroomBlockStackImpl(ItemStackInitInfo initInfo,
                                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
