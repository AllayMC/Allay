package org.allaymc.server.item.impl.log;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.log.ItemStrippedOakLogStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemStrippedOakLogStackImpl extends ItemStackImpl implements ItemStrippedOakLogStack {
    public ItemStrippedOakLogStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}