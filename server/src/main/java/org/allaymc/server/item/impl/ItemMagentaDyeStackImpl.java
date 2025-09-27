package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemMagentaDyeStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemMagentaDyeStackImpl extends ItemStackImpl implements ItemMagentaDyeStack {
    public ItemMagentaDyeStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
