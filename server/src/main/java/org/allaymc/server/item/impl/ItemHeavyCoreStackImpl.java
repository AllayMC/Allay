package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemHeavyCoreStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemHeavyCoreStackImpl extends ItemStackImpl implements ItemHeavyCoreStack {
    public ItemHeavyCoreStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
