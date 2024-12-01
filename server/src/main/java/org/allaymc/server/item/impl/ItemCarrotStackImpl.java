package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCarrotStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemCarrotStackImpl extends ItemStackImpl implements ItemCarrotStack {
    @Delegate
    protected ItemFoodComponent foodComponent;

    public ItemCarrotStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
