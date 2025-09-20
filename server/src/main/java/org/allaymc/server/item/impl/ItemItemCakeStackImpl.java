package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemItemCakeStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemItemCakeStackImpl extends ItemStackImpl implements ItemItemCakeStack {
    public ItemItemCakeStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
