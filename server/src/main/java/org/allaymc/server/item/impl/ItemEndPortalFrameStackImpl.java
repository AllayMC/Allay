package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemEndPortalFrameStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemEndPortalFrameStackImpl extends ItemStackImpl implements ItemEndPortalFrameStack {
    public ItemEndPortalFrameStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
