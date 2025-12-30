package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.interfaces.ItemSpearStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemSpearStackImpl extends ItemStackImpl implements ItemSpearStack {
    @Delegate
    private ItemRepairableComponent repairableComponent;

    public ItemSpearStackImpl(ItemStackInitInfo initInfo,
                              List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
