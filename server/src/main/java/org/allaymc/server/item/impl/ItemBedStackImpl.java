package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBedStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemBedStackImpl extends ItemStackImpl implements ItemBedStack {
    public ItemBedStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
