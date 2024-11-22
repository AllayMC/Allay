package org.allaymc.server.item.impl.copper;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.copper.ItemWaxedExposedChiseledCopperStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemWaxedExposedChiseledCopperStackImpl extends ItemStackImpl implements ItemWaxedExposedChiseledCopperStack {
    public ItemWaxedExposedChiseledCopperStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
