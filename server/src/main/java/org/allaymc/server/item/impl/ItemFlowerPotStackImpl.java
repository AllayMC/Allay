package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFlowerPotStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemFlowerPotStackImpl extends ItemStackImpl implements ItemFlowerPotStack {
    public ItemFlowerPotStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
