package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemYellowCushionStack;
import org.allaymc.server.component.ComponentProvider;

public class ItemYellowCushionStackImpl extends ItemStackImpl implements ItemYellowCushionStack {
    public ItemYellowCushionStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
