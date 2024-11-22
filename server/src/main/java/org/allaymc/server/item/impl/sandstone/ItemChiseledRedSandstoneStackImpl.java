package org.allaymc.server.item.impl.sandstone;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.sandstone.ItemChiseledRedSandstoneStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemChiseledRedSandstoneStackImpl extends ItemStackImpl implements ItemChiseledRedSandstoneStack {
    public ItemChiseledRedSandstoneStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
