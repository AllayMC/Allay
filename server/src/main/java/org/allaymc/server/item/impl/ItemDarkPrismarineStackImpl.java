package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDarkPrismarineStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemDarkPrismarineStackImpl extends ItemStackImpl implements ItemDarkPrismarineStack {
    public ItemDarkPrismarineStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
