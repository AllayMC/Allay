package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBreadStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemBreadStackImpl extends ItemStackImpl implements ItemBreadStack {
    @Delegate
    protected ItemEdibleComponent edibleComponent;

    public ItemBreadStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
