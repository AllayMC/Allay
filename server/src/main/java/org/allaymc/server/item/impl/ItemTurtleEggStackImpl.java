package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemTurtleEggStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemTurtleEggStackImpl extends ItemStackImpl implements ItemTurtleEggStack {
    public ItemTurtleEggStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
