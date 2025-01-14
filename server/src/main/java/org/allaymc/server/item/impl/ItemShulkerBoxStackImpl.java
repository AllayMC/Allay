package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemStuffStorableComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemShulkerBoxStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemShulkerBoxStackImpl extends ItemStackImpl implements ItemShulkerBoxStack {
    @Delegate
    protected ItemStuffStorableComponent stuffStorableComponent;

    public ItemShulkerBoxStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
