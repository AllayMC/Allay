package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemTallgrass0Stack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemTallgrass0StackImpl extends ItemStackImpl implements ItemTallgrass0Stack {
    public ItemTallgrass0StackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
