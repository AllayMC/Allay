package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.component.ItemTrimmableComponent;
import org.allaymc.api.item.interfaces.ItemBootsStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemBootsStackImpl extends ItemStackImpl implements ItemBootsStack {

    @Delegate
    private ItemArmorBaseComponent armorBaseComponent;
    @Delegate
    private ItemTrimmableComponent trimComponent;

    public ItemBootsStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
