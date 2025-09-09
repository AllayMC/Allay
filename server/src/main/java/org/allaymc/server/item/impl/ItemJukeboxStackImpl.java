package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemJukeboxStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemJukeboxStackImpl extends ItemStackImpl implements ItemJukeboxStack {
    public ItemJukeboxStackImpl(ItemStackInitInfo initInfo,
                                List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
