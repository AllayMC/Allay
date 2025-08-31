package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemStrippedCrimsonStemStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemStrippedCrimsonStemStackImpl extends ItemStackImpl implements ItemStrippedCrimsonStemStack {
    public ItemStrippedCrimsonStemStackImpl(ItemStackInitInfo initInfo,
                                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
