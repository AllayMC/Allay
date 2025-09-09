package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemWoodenPressurePlateStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemWoodenPressurePlateStackImpl extends ItemStackImpl implements ItemWoodenPressurePlateStack {
    public ItemWoodenPressurePlateStackImpl(ItemStackInitInfo initInfo,
                                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
