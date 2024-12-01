package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPotionStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemPotionStackImpl extends ItemStackImpl implements ItemPotionStack {
    public ItemPotionStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
