package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBoneMealStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemBoneMealStackImpl extends ItemStackImpl implements ItemBoneMealStack {
    public ItemBoneMealStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
