package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemIronNuggetStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemIronNuggetStackImpl extends ItemStackImpl implements ItemIronNuggetStack {
    public ItemIronNuggetStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
