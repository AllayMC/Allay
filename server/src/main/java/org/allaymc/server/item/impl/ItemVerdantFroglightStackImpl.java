package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemVerdantFroglightStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemVerdantFroglightStackImpl extends ItemStackImpl implements ItemVerdantFroglightStack {
    public ItemVerdantFroglightStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
