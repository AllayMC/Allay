package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemOchreFroglightStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemOchreFroglightStackImpl extends ItemStackImpl implements ItemOchreFroglightStack {
    public ItemOchreFroglightStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
