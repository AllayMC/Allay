package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSculkSensorStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemSculkSensorStackImpl extends ItemStackImpl implements ItemSculkSensorStack {
    public ItemSculkSensorStackImpl(ItemStackInitInfo initInfo,
                                    List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
