package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemNetherSproutsStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemNetherSproutsStackImpl extends ItemStackImpl implements ItemNetherSproutsStack {
    public ItemNetherSproutsStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
