package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.interfaces.ItemPickaxeStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemPickaxeStackImpl extends ItemStackImpl implements ItemPickaxeStack {
    @Delegate
    private ItemRepairableComponent repairableComponent;

    public ItemPickaxeStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
