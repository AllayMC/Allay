package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemBoneBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemBoneBlockStackImpl extends ItemStackImpl implements ItemBoneBlockStack {
    public ItemBoneBlockStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
