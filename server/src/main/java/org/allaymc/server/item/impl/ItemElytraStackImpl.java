package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.interfaces.ItemElytraStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemElytraStackImpl extends ItemStackImpl implements ItemElytraStack {
    @Delegate
    protected ItemRepairableComponent repairableComponent;

    public ItemElytraStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    protected ItemArmorBaseComponent getBaseComponent() {
        return (ItemArmorBaseComponent) super.getBaseComponent();
    }
}
