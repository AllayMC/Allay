package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPotatoesStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemPotatoesStackImpl extends ItemStackImpl implements ItemPotatoesStack {
    public ItemPotatoesStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
