package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.component.ItemTrimmableComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLeggingsStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemLeggingsStackImpl extends ItemStackImpl implements ItemLeggingsStack {
    @Delegate
    protected ItemTrimmableComponent trimComponent;

    public ItemLeggingsStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    protected ItemArmorBaseComponent getBaseComponent() {
        return (ItemArmorBaseComponent) super.getBaseComponent();
    }
}
