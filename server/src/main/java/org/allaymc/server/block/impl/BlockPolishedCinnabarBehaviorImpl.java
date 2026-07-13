package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockPolishedCinnabarBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

public class BlockPolishedCinnabarBehaviorImpl extends BlockBehaviorImpl implements BlockPolishedCinnabarBehavior {
    public BlockPolishedCinnabarBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
