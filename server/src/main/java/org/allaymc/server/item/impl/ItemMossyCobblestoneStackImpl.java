package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMossyCobblestoneStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemMossyCobblestoneStackImpl extends ItemStackImpl implements ItemMossyCobblestoneStack {
    public ItemMossyCobblestoneStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
