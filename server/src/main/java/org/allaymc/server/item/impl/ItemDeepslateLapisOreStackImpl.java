package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDeepslateLapisOreStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemDeepslateLapisOreStackImpl extends ItemStackImpl implements ItemDeepslateLapisOreStack {
    public ItemDeepslateLapisOreStackImpl(ItemStackInitInfo initInfo,
                                          List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
