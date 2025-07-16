package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemWritableBookBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWritableBookStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemWritableBookStackImpl extends ItemStackImpl implements ItemWritableBookStack {
    public ItemWritableBookStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    protected ItemWritableBookBaseComponent getBaseComponent() {
        return (ItemWritableBookBaseComponent) super.getBaseComponent();
    }
}
