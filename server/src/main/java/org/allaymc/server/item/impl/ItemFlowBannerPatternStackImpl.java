package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFlowBannerPatternStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemFlowBannerPatternStackImpl extends ItemStackImpl implements ItemFlowBannerPatternStack {
    public ItemFlowBannerPatternStackImpl(ItemStackInitInfo initInfo,
                                          List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
