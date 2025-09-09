package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemOminousTrialKeyStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemOminousTrialKeyStackImpl extends ItemStackImpl implements ItemOminousTrialKeyStack {
    public ItemOminousTrialKeyStackImpl(ItemStackInitInfo initInfo,
                                        List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
