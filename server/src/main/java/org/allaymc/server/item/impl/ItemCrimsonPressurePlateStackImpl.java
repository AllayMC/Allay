package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCrimsonPressurePlateStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemCrimsonPressurePlateStackImpl extends ItemStackImpl implements ItemCrimsonPressurePlateStack {
    public ItemCrimsonPressurePlateStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
