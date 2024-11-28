package org.allaymc.server.item.impl;

import java.util.List;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCookedPorkchopStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemCookedPorkchopStackImpl extends ItemStackImpl implements ItemCookedPorkchopStack {
    @Delegate
    protected ItemFoodComponent foodComponent;

    public ItemCookedPorkchopStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
