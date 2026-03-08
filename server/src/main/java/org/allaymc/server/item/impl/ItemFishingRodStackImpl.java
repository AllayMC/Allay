package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.interfaces.ItemFishingRodStack;
import org.allaymc.server.component.ComponentProvider;
import org.allaymc.server.item.component.ItemFishingRodBaseComponentImpl;

import java.util.List;

public class ItemFishingRodStackImpl extends ItemStackImpl implements ItemFishingRodStack {

    @Delegate
    private ItemRepairableComponent repairableComponent;

    public ItemFishingRodStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
