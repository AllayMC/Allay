package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemNetherWartBlockStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemNetherWartBlockStackImpl extends ItemStackImpl implements ItemNetherWartBlockStack {
    public ItemNetherWartBlockStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
