package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemReserved6Stack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemReserved6StackImpl extends ItemStackImpl implements ItemReserved6Stack {
    public ItemReserved6StackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
