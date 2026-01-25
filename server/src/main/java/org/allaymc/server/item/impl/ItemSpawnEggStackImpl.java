package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemSpawnEggBaseComponent;
import org.allaymc.api.item.interfaces.ItemSpawnEggStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemSpawnEggStackImpl extends ItemStackImpl implements ItemSpawnEggStack {
    @Delegate
    private ItemSpawnEggBaseComponent spawnEggBaseComponent;

    public ItemSpawnEggStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
