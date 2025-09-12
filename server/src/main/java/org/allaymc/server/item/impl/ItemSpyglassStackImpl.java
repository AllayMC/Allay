package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSpyglassStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemSpyglassStackImpl extends ItemStackImpl implements ItemSpyglassStack {
    public ItemSpyglassStackImpl(ItemStackInitInfo initInfo,
                                 List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
