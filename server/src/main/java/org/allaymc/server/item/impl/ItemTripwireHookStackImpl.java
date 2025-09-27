package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemTripwireHookStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemTripwireHookStackImpl extends ItemStackImpl implements ItemTripwireHookStack {
    public ItemTripwireHookStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
