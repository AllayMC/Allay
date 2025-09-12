package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemUnpoweredComparatorStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemUnpoweredComparatorStackImpl extends ItemStackImpl implements ItemUnpoweredComparatorStack {
    public ItemUnpoweredComparatorStackImpl(ItemStackInitInfo initInfo,
                                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
