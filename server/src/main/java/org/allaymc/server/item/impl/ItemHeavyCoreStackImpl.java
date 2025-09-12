package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemHeavyCoreStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemHeavyCoreStackImpl extends ItemStackImpl implements ItemHeavyCoreStack {
    public ItemHeavyCoreStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
