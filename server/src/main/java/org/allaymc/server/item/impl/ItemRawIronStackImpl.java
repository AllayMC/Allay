package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRawIronStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemRawIronStackImpl extends ItemStackImpl implements ItemRawIronStack {
    public ItemRawIronStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
