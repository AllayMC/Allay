package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBambooRaftStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemBambooRaftStackImpl extends ItemStackImpl implements ItemBambooRaftStack {
    public ItemBambooRaftStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
