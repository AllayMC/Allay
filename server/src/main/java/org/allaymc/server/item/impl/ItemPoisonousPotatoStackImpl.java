package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPoisonousPotatoStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemPoisonousPotatoStackImpl extends ItemStackImpl implements ItemPoisonousPotatoStack {
    @Delegate
    protected ItemFoodComponent foodComponent;

    public ItemPoisonousPotatoStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
