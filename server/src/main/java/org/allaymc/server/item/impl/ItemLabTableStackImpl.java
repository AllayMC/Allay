package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLabTableStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemLabTableStackImpl extends ItemStackImpl implements ItemLabTableStack {
    public ItemLabTableStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
