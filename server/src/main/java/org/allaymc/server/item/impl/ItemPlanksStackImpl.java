package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPlanksStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemPlanksStackImpl extends ItemStackImpl implements ItemPlanksStack {
    public ItemPlanksStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
