package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemInfestedDeepslateStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemInfestedDeepslateStackImpl extends ItemStackImpl implements ItemInfestedDeepslateStack {
    public ItemInfestedDeepslateStackImpl(ItemStackInitInfo initInfo,
                                          List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
