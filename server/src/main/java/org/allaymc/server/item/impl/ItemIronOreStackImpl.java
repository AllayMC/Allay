package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemIronOreStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemIronOreStackImpl extends ItemStackImpl implements ItemIronOreStack {
    public ItemIronOreStackImpl(ItemStackInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
