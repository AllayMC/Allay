package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBeetrootSoupStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemBeetrootSoupStackImpl extends ItemStackImpl implements ItemBeetrootSoupStack {
    @Delegate
    protected ItemFoodComponent foodComponent;

    public ItemBeetrootSoupStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
