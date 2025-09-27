package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWaxedOxidizedCopperBulbStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWaxedOxidizedCopperBulbStackImpl extends ItemStackImpl implements ItemWaxedOxidizedCopperBulbStack {
    public ItemWaxedOxidizedCopperBulbStackImpl(ItemStackInitInfo initInfo,
                                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
