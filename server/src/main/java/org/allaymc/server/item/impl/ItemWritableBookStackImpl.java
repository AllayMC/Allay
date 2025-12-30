package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemWritableBookBaseComponent;
import org.allaymc.api.item.interfaces.ItemWritableBookStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWritableBookStackImpl extends ItemStackImpl implements ItemWritableBookStack {

    @Delegate
    private ItemWritableBookBaseComponent writableBookBaseComponent;

    public ItemWritableBookStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
