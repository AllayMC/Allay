package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemTrimmableComponent;
import org.allaymc.api.item.interfaces.ItemHelmetStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemHelmetStackImpl extends ItemStackImpl implements ItemHelmetStack {
    @Delegate
    private ItemArmorBaseComponent armorBaseComponent;
    @Delegate
    private ItemTrimmableComponent trimmableComponent;
    @Delegate
    private ItemRepairableComponent repairableComponent;

    public ItemHelmetStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
