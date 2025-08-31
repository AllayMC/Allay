package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemGlowInkSacStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemGlowInkSacStackImpl extends ItemStackImpl implements ItemGlowInkSacStack {
    public ItemGlowInkSacStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
