package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWeatheredCopperBulbStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWeatheredCopperBulbStackImpl extends ItemStackImpl implements ItemWeatheredCopperBulbStack {
    public ItemWeatheredCopperBulbStackImpl(ItemStackInitInfo initInfo,
                                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
