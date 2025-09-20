package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDetectorRailStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemDetectorRailStackImpl extends ItemStackImpl implements ItemDetectorRailStack {
    public ItemDetectorRailStackImpl(ItemStackInitInfo initInfo,
                                     List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
