package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPotatoStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemPotatoStackImpl extends ItemStackImpl implements ItemPotatoStack {
    @Delegate
    protected ItemFoodComponent foodComponent;

    public ItemPotatoStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
