package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSeaLanternStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemSeaLanternStackImpl extends ItemStackImpl implements ItemSeaLanternStack {
    public ItemSeaLanternStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
