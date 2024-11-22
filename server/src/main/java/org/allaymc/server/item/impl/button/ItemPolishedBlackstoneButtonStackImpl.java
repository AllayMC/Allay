package org.allaymc.server.item.impl.button;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.button.ItemPolishedBlackstoneButtonStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemPolishedBlackstoneButtonStackImpl extends ItemStackImpl implements ItemPolishedBlackstoneButtonStack {
    public ItemPolishedBlackstoneButtonStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
