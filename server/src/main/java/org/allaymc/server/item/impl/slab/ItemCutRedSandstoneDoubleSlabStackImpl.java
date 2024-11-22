package org.allaymc.server.item.impl.slab;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.slab.ItemCutRedSandstoneDoubleSlabStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemCutRedSandstoneDoubleSlabStackImpl extends ItemStackImpl implements ItemCutRedSandstoneDoubleSlabStack {
    public ItemCutRedSandstoneDoubleSlabStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
