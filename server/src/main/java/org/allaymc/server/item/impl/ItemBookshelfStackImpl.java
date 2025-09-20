package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBookshelfStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemBookshelfStackImpl extends ItemStackImpl implements ItemBookshelfStack {
    public ItemBookshelfStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
