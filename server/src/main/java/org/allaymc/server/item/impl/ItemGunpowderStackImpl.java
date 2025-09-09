package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemGunpowderStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemGunpowderStackImpl extends ItemStackImpl implements ItemGunpowderStack {
    public ItemGunpowderStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
