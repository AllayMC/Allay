package org.allaymc.server.item.impl.trapdoor;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.trapdoor.ItemWarpedTrapdoorStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemWarpedTrapdoorStackImpl extends ItemStackImpl implements ItemWarpedTrapdoorStack {
    public ItemWarpedTrapdoorStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
