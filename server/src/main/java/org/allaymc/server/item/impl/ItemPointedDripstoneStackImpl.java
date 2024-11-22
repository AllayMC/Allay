package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPointedDripstoneStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemPointedDripstoneStackImpl extends ItemStackImpl implements ItemPointedDripstoneStack {
    public ItemPointedDripstoneStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
