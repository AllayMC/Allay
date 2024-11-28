package org.allaymc.server.item.impl;

import java.util.List;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBeefStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemBeefStackImpl extends ItemStackImpl implements ItemBeefStack {
    @Delegate
    protected ItemFoodComponent foodComponent;

    public ItemBeefStackImpl(ItemStackInitInfo initInfo,
                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
