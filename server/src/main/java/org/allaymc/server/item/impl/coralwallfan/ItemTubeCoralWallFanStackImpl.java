package org.allaymc.server.item.impl.coralwallfan;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.coralwallfan.ItemTubeCoralWallFanStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemTubeCoralWallFanStackImpl extends ItemStackImpl implements ItemTubeCoralWallFanStack {
    public ItemTubeCoralWallFanStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
