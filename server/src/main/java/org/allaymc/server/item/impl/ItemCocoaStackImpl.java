package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCocoaStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemCocoaStackImpl extends ItemStackImpl implements ItemCocoaStack {
    public ItemCocoaStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
