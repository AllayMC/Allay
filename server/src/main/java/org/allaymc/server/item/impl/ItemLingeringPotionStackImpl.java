package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLingeringPotionStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemLingeringPotionStackImpl extends ItemStackImpl implements ItemLingeringPotionStack {
    public ItemLingeringPotionStackImpl(ItemStackInitInfo initInfo,
                                        List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
