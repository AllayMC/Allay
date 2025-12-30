package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.interfaces.ItemCodStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCodStackImpl extends ItemStackImpl implements ItemCodStack {
    @Delegate
    private ItemEdibleComponent edibleComponent;

    public ItemCodStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
