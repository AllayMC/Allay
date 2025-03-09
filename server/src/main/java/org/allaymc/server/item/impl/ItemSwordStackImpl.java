package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSwordStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemSwordStackImpl extends ItemStackImpl implements ItemSwordStack {
    @Delegate
    protected ItemRepairableComponent repairableComponent;

    public ItemSwordStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
