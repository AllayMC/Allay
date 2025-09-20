package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCarrotOnAStickStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemCarrotOnAStickStackImpl extends ItemStackImpl implements ItemCarrotOnAStickStack {
    public ItemCarrotOnAStickStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
