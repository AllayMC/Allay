package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemInfoUpdate2Stack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemInfoUpdate2StackImpl extends ItemStackImpl implements ItemInfoUpdate2Stack {
    public ItemInfoUpdate2StackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
