package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPurpleDyeStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemPurpleDyeStackImpl extends ItemStackImpl implements ItemPurpleDyeStack {
    public ItemPurpleDyeStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
