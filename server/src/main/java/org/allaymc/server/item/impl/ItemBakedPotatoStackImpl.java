package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBakedPotatoStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemBakedPotatoStackImpl extends ItemStackImpl implements ItemBakedPotatoStack {
    @Delegate
    protected ItemFoodComponent foodComponent;

    public ItemBakedPotatoStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
