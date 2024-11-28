package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBeeNestStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemBeeNestStackImpl extends ItemStackImpl implements ItemBeeNestStack {
    public ItemBeeNestStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
