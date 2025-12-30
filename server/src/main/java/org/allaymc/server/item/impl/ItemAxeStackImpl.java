package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.interfaces.ItemAxeStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemAxeStackImpl extends ItemStackImpl implements ItemAxeStack {
    @Delegate
    private ItemRepairableComponent repairableComponent;

    public ItemAxeStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
