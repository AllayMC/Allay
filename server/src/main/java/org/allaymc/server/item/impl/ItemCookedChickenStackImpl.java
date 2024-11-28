package org.allaymc.server.item.impl;

import java.util.List;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCookedChickenStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemCookedChickenStackImpl extends ItemStackImpl implements ItemCookedChickenStack {
    @Delegate
    protected ItemFoodComponent foodComponent;

    public ItemCookedChickenStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
