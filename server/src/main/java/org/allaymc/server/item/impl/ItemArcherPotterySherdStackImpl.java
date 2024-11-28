package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemArcherPotterySherdStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemArcherPotterySherdStackImpl extends ItemStackImpl implements ItemArcherPotterySherdStack {
    public ItemArcherPotterySherdStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
