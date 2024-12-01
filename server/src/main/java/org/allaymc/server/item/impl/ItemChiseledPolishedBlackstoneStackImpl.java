package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemChiseledPolishedBlackstoneStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemChiseledPolishedBlackstoneStackImpl extends ItemStackImpl implements ItemChiseledPolishedBlackstoneStack {
    public ItemChiseledPolishedBlackstoneStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
