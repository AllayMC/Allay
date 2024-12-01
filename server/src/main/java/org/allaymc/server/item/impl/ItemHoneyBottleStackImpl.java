package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemHoneyBottleStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemHoneyBottleStackImpl extends ItemStackImpl implements ItemHoneyBottleStack {
    @Delegate
    protected ItemFoodComponent foodComponent;

    public ItemHoneyBottleStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
