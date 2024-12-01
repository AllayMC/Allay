package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemInfoUpdateStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemInfoUpdateStackImpl extends ItemStackImpl implements ItemInfoUpdateStack {
    public ItemInfoUpdateStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
