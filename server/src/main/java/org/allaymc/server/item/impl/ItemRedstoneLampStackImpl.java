package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRedstoneLampStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemRedstoneLampStackImpl extends ItemStackImpl implements ItemRedstoneLampStack {
    public ItemRedstoneLampStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
