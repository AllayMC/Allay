package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPortalBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPortalBehaviorImpl extends BlockBehaviorImpl implements BlockPortalBehavior {
    public BlockPortalBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
