package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDetectorRailStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemDetectorRailStackImpl extends ItemStackImpl implements ItemDetectorRailStack {
    public ItemDetectorRailStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
