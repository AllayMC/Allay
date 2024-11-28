package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemShroomlightStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemShroomlightStackImpl extends ItemStackImpl implements ItemShroomlightStack {
    public ItemShroomlightStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
