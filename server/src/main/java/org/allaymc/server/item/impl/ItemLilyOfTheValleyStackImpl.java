package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemLilyOfTheValleyStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemLilyOfTheValleyStackImpl extends ItemStackImpl implements ItemLilyOfTheValleyStack {
    public ItemLilyOfTheValleyStackImpl(ItemStackInitInfo initInfo,
                                        List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
