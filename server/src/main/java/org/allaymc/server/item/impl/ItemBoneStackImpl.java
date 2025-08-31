package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBoneStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemBoneStackImpl extends ItemStackImpl implements ItemBoneStack {
    public ItemBoneStackImpl(ItemStackInitInfo initInfo,
                             List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
