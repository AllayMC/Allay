package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemShelterPotterySherdStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemShelterPotterySherdStackImpl extends ItemStackImpl implements ItemShelterPotterySherdStack {
    public ItemShelterPotterySherdStackImpl(ItemStackInitInfo initInfo,
                                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
