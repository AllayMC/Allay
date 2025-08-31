package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemGlowLichenStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemGlowLichenStackImpl extends ItemStackImpl implements ItemGlowLichenStack {
    public ItemGlowLichenStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
