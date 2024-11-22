package org.allaymc.server.item.impl.copper;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.copper.ItemWaxedOxidizedCutCopperStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemWaxedOxidizedCutCopperStackImpl extends ItemStackImpl implements ItemWaxedOxidizedCutCopperStack {
    public ItemWaxedOxidizedCutCopperStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
