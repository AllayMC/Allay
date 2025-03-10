package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemHoeStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemHoeStackImpl extends ItemStackImpl implements ItemHoeStack {
    @Delegate
    protected ItemRepairableComponent repairableComponent;

    public ItemHoeStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
