package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemGoldenCarrotStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemGoldenCarrotStackImpl extends ItemStackImpl implements ItemGoldenCarrotStack {
    @Delegate
    protected ItemFoodComponent foodComponent;

    public ItemGoldenCarrotStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
