package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemStuffStorableComponent;
import org.allaymc.api.item.interfaces.ItemShulkerBoxStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemShulkerBoxStackImpl extends ItemStackImpl implements ItemShulkerBoxStack {
    @Delegate
    private ItemStuffStorableComponent stuffStorableComponent;

    public ItemShulkerBoxStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
