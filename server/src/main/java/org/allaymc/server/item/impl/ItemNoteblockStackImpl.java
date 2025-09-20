package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemNoteblockStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemNoteblockStackImpl extends ItemStackImpl implements ItemNoteblockStack {
    public ItemNoteblockStackImpl(ItemStackInitInfo initInfo,
                                  List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
