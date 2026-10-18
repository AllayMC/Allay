package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLimeCushionStack;
import org.allaymc.server.component.ComponentProvider;

public class ItemLimeCushionStackImpl extends ItemStackImpl implements ItemLimeCushionStack {
    public ItemLimeCushionStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
