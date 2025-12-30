package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.interfaces.ItemPoisonousPotatoStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemPoisonousPotatoStackImpl extends ItemStackImpl implements ItemPoisonousPotatoStack {
    @Delegate
    private ItemEdibleComponent edibleComponent;

    public ItemPoisonousPotatoStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
