package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRawIronBlockStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemRawIronBlockStackImpl extends ItemStackImpl implements ItemRawIronBlockStack {
    public ItemRawIronBlockStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
