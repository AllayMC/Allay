package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCopperChainStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCopperChainStackImpl extends ItemStackImpl implements ItemCopperChainStack {
    public ItemCopperChainStackImpl(ItemStackInitInfo initInfo,
                                    List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
