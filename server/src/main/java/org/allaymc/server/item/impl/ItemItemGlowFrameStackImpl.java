package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemItemGlowFrameStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemItemGlowFrameStackImpl extends ItemStackImpl implements ItemItemGlowFrameStack {
    public ItemItemGlowFrameStackImpl(ItemStackInitInfo initInfo,
                                      List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
