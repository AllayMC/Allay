package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWaxedCopperBulbStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWaxedCopperBulbStackImpl extends ItemStackImpl implements ItemWaxedCopperBulbStack {
    public ItemWaxedCopperBulbStackImpl(ItemStackInitInfo initInfo,
                                        List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
