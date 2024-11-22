package org.allaymc.server.item.impl.slab;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.slab.ItemOxidizedCutCopperSlabStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemOxidizedCutCopperSlabStackImpl extends ItemStackImpl implements ItemOxidizedCutCopperSlabStack {
    public ItemOxidizedCutCopperSlabStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
