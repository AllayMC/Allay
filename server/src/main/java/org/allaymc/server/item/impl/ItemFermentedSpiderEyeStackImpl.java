package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFermentedSpiderEyeStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemFermentedSpiderEyeStackImpl extends ItemStackImpl implements ItemFermentedSpiderEyeStack {
    public ItemFermentedSpiderEyeStackImpl(ItemStackInitInfo initInfo,
                                           List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
