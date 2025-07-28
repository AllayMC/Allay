package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemChorusFruitStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemChorusFruitStackImpl extends ItemStackImpl implements ItemChorusFruitStack {
    @Delegate
    protected ItemEdibleComponent edibleComponent;

    public ItemChorusFruitStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
