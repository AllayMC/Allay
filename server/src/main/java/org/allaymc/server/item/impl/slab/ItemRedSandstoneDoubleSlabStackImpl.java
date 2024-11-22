package org.allaymc.server.item.impl.slab;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.slab.ItemRedSandstoneDoubleSlabStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemRedSandstoneDoubleSlabStackImpl extends ItemStackImpl implements ItemRedSandstoneDoubleSlabStack {
    public ItemRedSandstoneDoubleSlabStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
