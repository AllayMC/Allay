package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemEmeraldOreStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemEmeraldOreStackImpl extends ItemStackImpl implements ItemEmeraldOreStack {
    public ItemEmeraldOreStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
