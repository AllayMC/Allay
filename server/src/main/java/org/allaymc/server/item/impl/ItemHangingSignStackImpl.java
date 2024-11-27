package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemHangingSignStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemHangingSignStackImpl extends ItemStackImpl implements ItemHangingSignStack {
    public ItemHangingSignStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
