package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFlowBannerPatternStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemFlowBannerPatternStackImpl extends ItemStackImpl implements ItemFlowBannerPatternStack {
    public ItemFlowBannerPatternStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
