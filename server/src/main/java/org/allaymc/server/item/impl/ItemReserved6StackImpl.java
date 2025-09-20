package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemReserved6Stack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemReserved6StackImpl extends ItemStackImpl implements ItemReserved6Stack {
    public ItemReserved6StackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
