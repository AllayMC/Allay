package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemComposterStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemComposterStackImpl extends ItemStackImpl implements ItemComposterStack {
    public ItemComposterStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
