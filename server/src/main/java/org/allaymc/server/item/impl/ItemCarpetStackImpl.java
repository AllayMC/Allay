package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCarpetStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemCarpetStackImpl extends ItemStackImpl implements ItemCarpetStack {
    public ItemCarpetStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
