package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemTntStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemTntStackImpl extends ItemStackImpl implements ItemTntStack {
    public ItemTntStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
