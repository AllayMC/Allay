package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWaxedExposedCopperBulbStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWaxedExposedCopperBulbStackImpl extends ItemStackImpl implements ItemWaxedExposedCopperBulbStack {
    public ItemWaxedExposedCopperBulbStackImpl(ItemStackInitInfo initInfo,
                                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
