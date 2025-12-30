package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.interfaces.ItemRottenFleshStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemRottenFleshStackImpl extends ItemStackImpl implements ItemRottenFleshStack {
    @Delegate
    private ItemEdibleComponent edibleComponent;

    public ItemRottenFleshStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
