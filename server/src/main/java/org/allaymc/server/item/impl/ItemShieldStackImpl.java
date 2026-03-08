package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemShieldBaseComponent;
import org.allaymc.api.item.interfaces.ItemShieldStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemShieldStackImpl extends ItemStackImpl implements ItemShieldStack {
    @Delegate
    private ItemShieldBaseComponent shieldBaseComponent;

    @Delegate
    private ItemRepairableComponent repairableComponent;

    public ItemShieldStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
