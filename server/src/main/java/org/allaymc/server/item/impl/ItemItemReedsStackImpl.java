package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemItemReedsStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemItemReedsStackImpl extends ItemStackImpl implements ItemItemReedsStack {
    public ItemItemReedsStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
