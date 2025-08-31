package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemVaultStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemVaultStackImpl extends ItemStackImpl implements ItemVaultStack {
    public ItemVaultStackImpl(ItemStackInitInfo initInfo,
                              List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
