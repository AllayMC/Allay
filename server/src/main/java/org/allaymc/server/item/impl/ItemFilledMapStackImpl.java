package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemFilledMapBaseComponent;
import org.allaymc.api.item.interfaces.ItemFilledMapStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemFilledMapStackImpl extends ItemStackImpl implements ItemFilledMapStack {
    public ItemFilledMapStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    protected ItemFilledMapBaseComponent getBaseComponent() {
        return (ItemFilledMapBaseComponent) super.getBaseComponent();
    }
}
