package org.allaymc.server.item.impl;

import java.util.List;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPumpkinPieStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemPumpkinPieStackImpl extends ItemStackImpl implements ItemPumpkinPieStack {
    @Delegate
    protected ItemFoodComponent foodComponent;

    public ItemPumpkinPieStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
