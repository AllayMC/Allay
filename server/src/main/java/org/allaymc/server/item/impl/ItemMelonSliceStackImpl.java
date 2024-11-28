package org.allaymc.server.item.impl;

import java.util.List;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMelonSliceStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemMelonSliceStackImpl extends ItemStackImpl implements ItemMelonSliceStack {
    @Delegate
    protected ItemFoodComponent foodComponent;

    public ItemMelonSliceStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
