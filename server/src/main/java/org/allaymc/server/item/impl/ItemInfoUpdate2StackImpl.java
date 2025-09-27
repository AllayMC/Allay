package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemInfoUpdate2Stack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemInfoUpdate2StackImpl extends ItemStackImpl implements ItemInfoUpdate2Stack {
    public ItemInfoUpdate2StackImpl(ItemStackInitInfo initInfo,
                                    List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
