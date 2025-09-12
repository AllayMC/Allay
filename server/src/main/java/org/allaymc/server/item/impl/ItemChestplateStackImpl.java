package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.component.ItemTrimmableComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemChestplateStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemChestplateStackImpl extends ItemStackImpl implements ItemChestplateStack {
    @Delegate
    protected ItemTrimmableComponent trimmableComponent;
    @Delegate
    protected ItemRepairableComponent repairableComponent;

    public ItemChestplateStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    protected ItemArmorBaseComponent getBaseComponent() {
        return (ItemArmorBaseComponent) super.getBaseComponent();
    }
}
