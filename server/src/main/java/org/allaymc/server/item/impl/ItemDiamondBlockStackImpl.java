package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemDiamondBlockStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemDiamondBlockStackImpl extends ItemStackImpl implements ItemDiamondBlockStack {
    public ItemDiamondBlockStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
