package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMyceliumStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemMyceliumStackImpl extends ItemStackImpl implements ItemMyceliumStack {
    public ItemMyceliumStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
