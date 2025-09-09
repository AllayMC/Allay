package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemEndPortalFrameStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemEndPortalFrameStackImpl extends ItemStackImpl implements ItemEndPortalFrameStack {
    public ItemEndPortalFrameStackImpl(ItemStackInitInfo initInfo,
                                       List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
