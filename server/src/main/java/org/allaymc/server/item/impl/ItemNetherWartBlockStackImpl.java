package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemNetherWartBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemNetherWartBlockStackImpl extends ItemStackImpl implements ItemNetherWartBlockStack {
    public ItemNetherWartBlockStackImpl(ItemStackInitInfo initInfo,
                                        List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
