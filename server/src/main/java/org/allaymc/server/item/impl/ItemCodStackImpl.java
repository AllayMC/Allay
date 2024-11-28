package org.allaymc.server.item.impl;

import java.util.List;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemFoodComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCodStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemCodStackImpl extends ItemStackImpl implements ItemCodStack {
    @Delegate
    protected ItemFoodComponent foodComponent;

    public ItemCodStackImpl(ItemStackInitInfo initInfo,
                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
