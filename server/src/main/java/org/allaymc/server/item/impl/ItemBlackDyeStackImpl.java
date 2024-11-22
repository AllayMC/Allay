package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBlackDyeStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemBlackDyeStackImpl extends ItemStackImpl implements ItemBlackDyeStack {
    public ItemBlackDyeStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
