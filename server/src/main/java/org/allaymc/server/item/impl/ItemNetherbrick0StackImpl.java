package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemNetherbrick0Stack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemNetherbrick0StackImpl extends ItemStackImpl implements ItemNetherbrick0Stack {
    public ItemNetherbrick0StackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
