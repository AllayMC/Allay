package org.allaymc.server.item.impl.copper;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.copper.ItemWaxedChiseledCopperStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemWaxedChiseledCopperStackImpl extends ItemStackImpl implements ItemWaxedChiseledCopperStack {
    public ItemWaxedChiseledCopperStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
