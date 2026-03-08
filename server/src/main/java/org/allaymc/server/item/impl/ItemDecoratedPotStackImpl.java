package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemDecoratedPotBaseComponent;
import org.allaymc.api.item.interfaces.ItemDecoratedPotStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemDecoratedPotStackImpl extends ItemStackImpl implements ItemDecoratedPotStack {
    @Delegate
    private ItemDecoratedPotBaseComponent decoratedPotBaseComponent;

    public ItemDecoratedPotStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
