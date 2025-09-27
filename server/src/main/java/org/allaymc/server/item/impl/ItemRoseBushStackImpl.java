package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRoseBushStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemRoseBushStackImpl extends ItemStackImpl implements ItemRoseBushStack {
    public ItemRoseBushStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
