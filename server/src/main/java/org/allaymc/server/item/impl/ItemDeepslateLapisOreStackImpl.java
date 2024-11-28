package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDeepslateLapisOreStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemDeepslateLapisOreStackImpl extends ItemStackImpl implements ItemDeepslateLapisOreStack {
    public ItemDeepslateLapisOreStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
