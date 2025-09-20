package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemTwistingVinesStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemTwistingVinesStackImpl extends ItemStackImpl implements ItemTwistingVinesStack {
    public ItemTwistingVinesStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
