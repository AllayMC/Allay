package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRedstoneBlockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemRedstoneBlockStackImpl extends ItemStackImpl implements ItemRedstoneBlockStack {
    public ItemRedstoneBlockStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
