package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.component.ItemDyeableComponent;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemTrimmableComponent;
import org.allaymc.api.item.interfaces.ItemLeatherChestplateStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemLeatherChestplateStackImpl extends ItemStackImpl implements ItemLeatherChestplateStack {
    @Delegate
    private ItemArmorBaseComponent armorBaseComponent;
    @Delegate
    private ItemTrimmableComponent trimmableComponent;
    @Delegate
    private ItemRepairableComponent repairableComponent;
    @Delegate
    private ItemDyeableComponent dyeableComponent;

    public ItemLeatherChestplateStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
