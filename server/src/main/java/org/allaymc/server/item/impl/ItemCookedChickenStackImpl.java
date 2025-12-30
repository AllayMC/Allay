package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.api.item.interfaces.ItemCookedChickenStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCookedChickenStackImpl extends ItemStackImpl implements ItemCookedChickenStack {
    @Delegate
    private ItemEdibleComponent edibleComponent;

    public ItemCookedChickenStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
