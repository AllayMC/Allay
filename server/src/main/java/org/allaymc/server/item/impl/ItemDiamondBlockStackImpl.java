package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDiamondBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemDiamondBlockStackImpl extends ItemStackImpl implements ItemDiamondBlockStack {
    public ItemDiamondBlockStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
