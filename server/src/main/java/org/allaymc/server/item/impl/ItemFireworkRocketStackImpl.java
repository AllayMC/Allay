package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemFireworkRocketStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemFireworkRocketStackImpl extends ItemStackImpl implements ItemFireworkRocketStack {
    public ItemFireworkRocketStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
