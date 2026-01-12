package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemCrossbowBaseComponent;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.interfaces.ItemCrossbowStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCrossbowStackImpl extends ItemStackImpl implements ItemCrossbowStack {

    @Delegate
    private ItemCrossbowBaseComponent crossbowBaseComponent;
    @Delegate
    private ItemRepairableComponent repairableComponent;

    public ItemCrossbowStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
