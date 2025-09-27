package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemVerdantFroglightStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemVerdantFroglightStackImpl extends ItemStackImpl implements ItemVerdantFroglightStack {
    public ItemVerdantFroglightStackImpl(ItemStackInitInfo initInfo,
                                         List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
