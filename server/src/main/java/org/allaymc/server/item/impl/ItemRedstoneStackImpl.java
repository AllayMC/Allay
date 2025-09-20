package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRedstoneStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemRedstoneStackImpl extends ItemStackImpl implements ItemRedstoneStack {
    public ItemRedstoneStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
