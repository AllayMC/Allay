package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCocoaStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemCocoaStackImpl extends ItemStackImpl implements ItemCocoaStack {
    public ItemCocoaStackImpl(ItemStackInitInfo initInfo,
                              List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
