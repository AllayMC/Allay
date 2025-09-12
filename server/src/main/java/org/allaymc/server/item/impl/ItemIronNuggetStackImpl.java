package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemIronNuggetStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemIronNuggetStackImpl extends ItemStackImpl implements ItemIronNuggetStack {
    public ItemIronNuggetStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
