package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRedFlowerStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemRedFlowerStackImpl extends ItemStackImpl implements ItemRedFlowerStack {
    public ItemRedFlowerStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
