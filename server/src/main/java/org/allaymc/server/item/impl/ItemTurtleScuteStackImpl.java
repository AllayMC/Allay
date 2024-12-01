package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemTurtleScuteStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemTurtleScuteStackImpl extends ItemStackImpl implements ItemTurtleScuteStack {
    public ItemTurtleScuteStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
