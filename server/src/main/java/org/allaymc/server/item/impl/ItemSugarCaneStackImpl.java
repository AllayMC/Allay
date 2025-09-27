package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSugarCaneStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemSugarCaneStackImpl extends ItemStackImpl implements ItemSugarCaneStack {
    public ItemSugarCaneStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
