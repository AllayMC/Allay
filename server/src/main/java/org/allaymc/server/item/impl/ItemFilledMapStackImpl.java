package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFilledMapStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemFilledMapStackImpl extends ItemStackImpl implements ItemFilledMapStack {
    public ItemFilledMapStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
