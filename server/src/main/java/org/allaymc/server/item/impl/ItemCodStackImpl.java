package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCodStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemCodStackImpl extends ItemStackImpl implements ItemCodStack {
    @Delegate
    protected ItemEdibleComponent edibleComponent;

    public ItemCodStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
