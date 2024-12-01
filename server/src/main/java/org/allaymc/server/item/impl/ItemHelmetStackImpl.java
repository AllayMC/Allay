package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemHelmetStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemHelmetStackImpl extends ItemStackImpl implements ItemHelmetStack {
    @Delegate
    protected ItemArmorBaseComponent armorBaseComponent;

    public ItemHelmetStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
