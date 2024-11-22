package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPufferfishStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemPufferfishStackImpl extends ItemStackImpl implements ItemPufferfishStack {
    @Delegate
    protected ItemFoodComponent foodComponent;

    public ItemPufferfishStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
